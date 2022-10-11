package np222kk.dv507.a2.sematicalanalysis;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import np222kk.dv507.a2.language.OFPLanguageParser.ArrayDeclarationContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ArrayDeclarationInitializationContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ArrayDeclarationSizeContext;
import np222kk.dv507.a2.language.OFPLanguageParser.AssignmentContext;
import np222kk.dv507.a2.language.OFPLanguageParser.AssignmentRightContext;
import np222kk.dv507.a2.language.OFPLanguageParser.AttributeAccessContext;
import np222kk.dv507.a2.language.OFPLanguageParser.BinaryOpCalcContext;
import np222kk.dv507.a2.language.OFPLanguageParser.BoolValueContext;
import np222kk.dv507.a2.language.OFPLanguageParser.BracketsExprContext;
import np222kk.dv507.a2.language.OFPLanguageParser.CalcObjectContext;
import np222kk.dv507.a2.language.OFPLanguageParser.CalculationBracketsContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ConditionContext;
import np222kk.dv507.a2.language.OFPLanguageParser.DeclarationContext;
import np222kk.dv507.a2.language.OFPLanguageParser.EqualsCompContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ExpressionContext;
import np222kk.dv507.a2.language.OFPLanguageParser.LessCompContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MainMethodContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MethodArrayAccessContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MethodCallContext;
import np222kk.dv507.a2.language.OFPLanguageParser.OptionalStatementGroupContext;
import np222kk.dv507.a2.language.OFPLanguageParser.PrintStatementContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ReturnStatementContext;
import np222kk.dv507.a2.language.OFPLanguageParser.StartContext;
import np222kk.dv507.a2.language.OFPLanguageParser.SubMethodContext;
import np222kk.dv507.a2.language.OFPLanguageParser.UnaryMinusExprContext;
import np222kk.dv507.a2.language.OFPLanguageParser.VariableArrayAccessContext;
import np222kk.dv507.a2.language.OFPLanguageParser.VariableContext;
import np222kk.dv507.a2.sematicalanalysis.error.Error;
import np222kk.dv507.a2.sematicalanalysis.error.SemanticErrorHandler;
import np222kk.dv507.a2.sematicalanalysis.symbol.FunctionSymbol;
import np222kk.dv507.a2.language.OFPLanguageBaseVisitor;

public class TypeCheckingVisitor extends OFPLanguageBaseVisitor<OFPType>{

    private ParseTreeProperty<Scope> scopes;
    private Scope currentScope;
    private Scope functionScope;
    private FunctionSymbol currentFunction;
    private SemanticErrorHandler errorHandler;

    public TypeCheckingVisitor(ParseTreeProperty<Scope> scopes, SemanticErrorHandler errorHandler) {
        this.scopes = scopes;
        this.errorHandler = errorHandler;
    }

    public OFPType visitAllChildren(ParseTree node) {
        for(int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            visit(child);            
        }
        return null;
    }

    //#region Methods
    /*
     * **********************************************************************
     *                             Methods
     * **********************************************************************
     */

    @Override   // start: subMethod* mainMethod subMethod*;
    public OFPType visitStart(StartContext ctx) {
        currentScope = scopes.get(ctx);
        functionScope = scopes.get(ctx);
        visitAllChildren(ctx);
        return null;
    }


    @Override   // mainMethod: 'void' 'main' '(' ')' statementGroup;
    public OFPType visitMainMethod(MainMethodContext ctx) {
        currentScope = scopes.get(ctx);
        currentFunction = (FunctionSymbol) scopes.get(ctx).resolve("main");
        visitAllChildren(ctx);
        currentScope = currentScope.getEnclosingScope();
        currentFunction = null;
        return null;
    }

    @Override   // subMethod: (type | 'void') NAME '(' methodParameters? ')' statementGroup;
    public OFPType visitSubMethod(SubMethodContext ctx) {
        String name = ctx.NAME().toString();
        currentScope = scopes.get(ctx);
        currentFunction = (FunctionSymbol) scopes.get(ctx).resolve(name);
        visitAllChildren(ctx);
        currentScope = currentScope.getEnclosingScope();
        currentFunction = null;
        return null;
    }
    //#endregion


    //#region Statements
    /*
     * **********************************************************************
     *                             Statements
     * **********************************************************************
     */
    
    @Override   // optionalStatementGroup: statement | statementGroup;
    public OFPType visitOptionalStatementGroup(OptionalStatementGroupContext ctx) {
        currentScope = scopes.get(ctx);
        visitAllChildren(ctx);
        currentScope = currentScope.getEnclosingScope();
        return null;
    }

    /** 
     * Visit conditions of if and while statements
     * If and while stepover in visit all children same with else if and else
     */
    @Override   // condition: '(' expression ')';
    public OFPType visitCondition(ConditionContext ctx) {
        ExpressionContext condition = ctx.expression();
        OFPType identifiedType = visit(condition);
        if (identifiedType != OFPType.booleanType) {
            reportTypeError(condition, OFPType.booleanType, identifiedType);
        }
        return null;
    }

    @Override   // returnStatement: 'return' (expression|arrayDeclaration);
    public OFPType visitReturnStatement(ReturnStatementContext ctx) {

        OFPType expectedType = currentFunction.getType();
        OFPType identifiedType = visit(ctx.getChild(1));
        
        if (expectedType == null) {
            errorHandler.reportError(
                new Error(ctx, "Return statement not allowed in function with return type void"));
            return null;
        }

        if (identifiedType != expectedType) {
            String expectedTypeName = "void";
            if (expectedType != null) {
                expectedTypeName = expectedType.getName();
            }

            String identifiedTypeName = getTypeAsString(identifiedType);
            reportTypeError(ctx, expectedTypeName, identifiedTypeName);
        }

        return null;
    }

    @Override   // printStatement: ('print'|'println') '(' expression ')';
    public OFPType visitPrintStatement(PrintStatementContext ctx) {
        ExpressionContext printContent = ctx.expression();
        OFPType identfiedType = visit(printContent);
        if (OFPType.isArrayType(identfiedType)) {
            reportUnsupportedTypeError(ctx, OFPType.getBasicTypes(), identfiedType);
        }
        return null;
    }

    @Override   // declaration: type NAME assignmentRight?;
    public OFPType visitDeclaration(DeclarationContext ctx) {
        if (ctx.assignmentRight() != null) {
            OFPType expectedType = OFPType.getTypeFor(ctx.type().getChild(0).toString());
            OFPType identifiedType = visitAssignmentRight(ctx.assignmentRight());
            if (expectedType != identifiedType) {
                reportTypeError(ctx.assignmentRight(), expectedType, identifiedType);
            }
        }
        return null;
    }

    @Override   // assignment: (variable | variableArrayAccess) assignmentRight;
    public OFPType visitAssignment(AssignmentContext ctx) {
        OFPType expectedType = visit(ctx.getChild(0)); // first child is variable or variableArrayAccess
        OFPType identifiedType = visitAssignmentRight(ctx.assignmentRight());
        if (expectedType != identifiedType) {
            reportTypeError(ctx.assignmentRight(), expectedType, identifiedType);
        }

        // Check if user tries to change element in string
        if (expectedType == OFPType.charType && ctx.variableArrayAccess() != null) {
            // Go to variable declaration if there is an array access and get the type
            if (visitVariable(ctx.variableArrayAccess().variable()) == OFPType.stringType) {
                    errorHandler.reportError(
                        new Error(ctx.variableArrayAccess(), "It is not allowed to change a string"));   
            }
        }

        return null;
    }

    @Override   // assignmentRight: ASSIGN (arrayDeclaration|expression);
    public OFPType visitAssignmentRight(AssignmentRightContext ctx) {
        return visit(ctx.getChild(1));
    }

    @Override   // arrayDeclaration: arrayDeclarationSize | arrayDeclarationInitialization;
    public OFPType visitArrayDeclaration(ArrayDeclarationContext ctx) {
        if (ctx.arrayDeclarationInitialization() != null) {
            return visitArrayDeclarationInitialization(ctx.arrayDeclarationInitialization());
        } else {
            return visitArrayDeclarationSize(ctx.arrayDeclarationSize());
        }
    }
	
	@Override   // arrayDeclarationSize: 'new' (INT|CHAR|FLOAT) '[' expression ']';
    public OFPType visitArrayDeclarationSize(ArrayDeclarationSizeContext ctx) {

        OFPType identifiedType = visit(ctx.expression());
        if (identifiedType != OFPType.intType) {
            reportTypeError(ctx, OFPType.intType, identifiedType);
        }

        return OFPType.getArrayTypeForElementType(OFPType.getTypeFor(ctx.getChild(1).toString()));
    }
	
	@Override   // arrayDeclarationInitialization: '{' (expression(',' expression)*)? '}';
    public OFPType visitArrayDeclarationInitialization(ArrayDeclarationInitializationContext ctx) {
        OFPType firstType = null; 
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof ExpressionContext) {
                if (firstType == null) {
                    firstType = visit((ExpressionContext) ctx.getChild(i));
                } else {
                    OFPType identifiedType = visit((ExpressionContext) ctx.getChild(i));
                    if (identifiedType != firstType) {
                        errorHandler.reportError(
                            new Error(ctx, "Different types recognized in array initialization"));
                    }
                }
            }
        }
        
        return OFPType.getArrayTypeForElementType(firstType);
    }


    //#endregion


    //#region Expressions
    /*
     * **********************************************************************
     *                             Expressions
     * **********************************************************************
     */

    @Override   // expression (LESS|GREATER) expression
    public OFPType visitLessComp(LessCompContext ctx) {
        OFPType firstType = visit(ctx.expression(0));
        OFPType secondType = visit(ctx.expression(1));
        if (firstType != secondType) {
            unequalTypesError(ctx, firstType, secondType);   
        }

        List<OFPType> supportedTypes = new ArrayList<OFPType>(2);
        supportedTypes.add(OFPType.intType);
        supportedTypes.add(OFPType.floatType);

        if (!supportedTypes.contains(firstType)) {
            reportUnsupportedTypeError(ctx.expression(0), supportedTypes, firstType);
        }

        if (!supportedTypes.contains(secondType)) {
            reportUnsupportedTypeError(ctx.expression(0), supportedTypes, secondType);
        }

        return OFPType.booleanType;
    }

    @Override   // expression EQUALS expression
    public OFPType visitEqualsComp(EqualsCompContext ctx) {
        OFPType firstType = visit(ctx.expression(0));
        OFPType secondType = visit(ctx.expression(1));
        if (firstType != secondType) {
            unequalTypesError(ctx, firstType, secondType);   
        }

        List<OFPType> supportedTypes = new ArrayList<OFPType>(3);
        supportedTypes.add(OFPType.intType);
        supportedTypes.add(OFPType.floatType);
        supportedTypes.add(OFPType.charType);

        if (!supportedTypes.contains(firstType)) {
            reportUnsupportedTypeError(ctx.expression(0), supportedTypes, firstType);
        }

        if (!supportedTypes.contains(secondType)) {
            reportUnsupportedTypeError(ctx.expression(0), supportedTypes, secondType);
        }

        return OFPType.booleanType;
    }

    @Override   // expression (PLUS|MINUS) expression
                // expression (MULT|DIV) expression
    public OFPType visitBinaryOpCalc(BinaryOpCalcContext ctx) {
        OFPType firstType = visit(ctx.expression(0));
        OFPType secondType = visit(ctx.expression(1));
        if (firstType != secondType) {
            unequalTypesError(ctx, firstType, secondType);   
        }

        List<OFPType> supportedTypes = new ArrayList<OFPType>(2);
        supportedTypes.add(OFPType.intType);
        supportedTypes.add(OFPType.floatType);

        if (!supportedTypes.contains(firstType)) {
            reportUnsupportedTypeError(ctx.expression(0), supportedTypes, firstType);
        }

        if (!supportedTypes.contains(secondType)) {
            reportUnsupportedTypeError(ctx.expression(0), supportedTypes, secondType);
        }

        return firstType;
    }

    @Override   // MINUS (calculationBrackets | calcObject)
    public OFPType visitUnaryMinusExpr(UnaryMinusExprContext ctx) {
        OFPType type = visit(ctx.getChild(1));
        
        List<OFPType> supportedTypes = new ArrayList<OFPType>(2);
        supportedTypes.add(OFPType.intType);
        supportedTypes.add(OFPType.floatType);
        
        if (!supportedTypes.contains(type)) {
            reportUnsupportedTypeError(ctx, supportedTypes, type);
        }

        return type;        
    }

    @Override   // calculationBrackets
    public OFPType visitBracketsExpr(BracketsExprContext ctx) {
        return visit(ctx.calculationBrackets());
    }

    @Override   // calculationBrackets: '(' expression ')';
    public OFPType visitCalculationBrackets(CalculationBracketsContext ctx) {
        return visit(ctx.expression());
    }

    //#endregion


    //#region Basics
    /*
     * **********************************************************************
     *                             Basics
     * **********************************************************************
     */

    @Override   // calcObject: FLOATSPEC | INTSPEC | CHARSPEC | STRINGSPEC | boolValue | attributeAccess | variableArrayAccess | methodArrayAccess | variable | methodCall;
    public OFPType visitCalcObject(CalcObjectContext ctx) {

        if (ctx.FLOATSPEC() != null) {
            return OFPType.floatType;
        } else if (ctx.INTSPEC() != null) {
            return OFPType.intType;
        } else if (ctx.CHARSPEC() != null) {
            return OFPType.charType;
        } else if (ctx.STRINGSPEC() != null) {
            return OFPType.stringType;
        } else {
            // attributeAccess | variableArrayAccess
            // methodArrayAccess | variable | methodCall
            return visit(ctx.getChild(0));
        }
    }


    @Override   // attributeAccess: (variable | methodCall) '.' NAME;
    public OFPType visitAttributeAccess(AttributeAccessContext ctx) {
        OFPType type = visit(ctx.getChild(0));
        if ((OFPType.isArrayType(type) || type == OFPType.stringType) == false) {
            errorHandler.reportError(
                new Error(ctx, "No attribute access possible for type " + type.getName()));
        }
        if (!ctx.NAME().toString().equals("length")) {
            errorHandler.reportError(
                new Error(ctx, "Unknown attribute " + ctx.NAME().toString()));
        }

        return OFPType.intType; // always int only length attribute
    }

    @Override   // methodArrayAccess: methodCall '[' expression ']';
    public OFPType visitMethodArrayAccess(MethodArrayAccessContext ctx) {
        OFPType methodCallType = visit(ctx.methodCall());

        if (!(OFPType.isArrayType(methodCallType) || methodCallType == OFPType.stringType)) {
            errorHandler.reportError(
                new Error(ctx, "No array access possible for type " + methodCallType.getName()));
        }

        // Check type in calculation must be int
        OFPType identifiedType = visit(ctx.expression());
        if (identifiedType != OFPType.intType) {
            reportTypeError(ctx.expression(), OFPType.intType, identifiedType);
        }

        return OFPType.getElementTypeForArrayType(methodCallType);
    }

    @Override   // methodCall: NAME '(' methodCallParameters? ')';
    public OFPType visitMethodCall(MethodCallContext ctx) {
        FunctionSymbol function = (FunctionSymbol) this.functionScope.resolve(ctx.NAME().getText());
        
        // Check parameters
        if (ctx.methodCallParameters() != null) {
            int parameterCounter = 0;
            for (int i = 0; i < ctx.methodCallParameters().getChildCount(); i++) {
                ParseTree child = ctx.methodCallParameters().getChild(i);
                if (child instanceof ExpressionContext) {
                    OFPType currentChildType = visit(child);
                    OFPType expectedType = function.getParameter(parameterCounter).getType();
                    if (!(expectedType == currentChildType)){
                        errorHandler.reportError(
                            new Error(ctx, "Parameter type insufficient: Expected " + expectedType.getName() + " Identified " + currentChildType.getName(),
                            ((ExpressionContext) child).getStart().getLine(), ((ExpressionContext) child).getStart().getCharPositionInLine()));
                    }
                    parameterCounter++;
                }
            }
        }

        return function.getType();
    }

    @Override   // variableArrayAccess: variable '[' expression ']';
    public OFPType visitVariableArrayAccess(VariableArrayAccessContext ctx) {
        OFPType variableType = visit(ctx.variable());

        if (!(OFPType.isArrayType(variableType) || variableType == OFPType.stringType)) {
            errorHandler.reportError(
                new Error(ctx, "No array access possible for type " + variableType.getName()));
        }

        // Check type in calculation must be int
        OFPType identifiedType = visit(ctx.expression());
        if (identifiedType != OFPType.intType) {
            reportTypeError(ctx.expression(), OFPType.intType, identifiedType);
        }

        // Return type
        return OFPType.getElementTypeForArrayType(variableType);
    }

    @Override   // variable: NAME
    public OFPType visitVariable(VariableContext ctx) {
        return currentScope.resolve(ctx.NAME().getText()).getType();
    }

    @Override   // boolValue: TRUE | FALSE;
    public OFPType visitBoolValue(BoolValueContext ctx) {
        return OFPType.booleanType;
    }

    //#endregion


    //#region Error reporting
    /*
     * **********************************************************************
     *                             Error reporting
     * **********************************************************************
     */

    private String getTypeAsString(OFPType type) {
        String typeString = "'Type not identifiable'";
        if (type != null) {
            typeString = type.getName();
        }
        return typeString;
    }

    private void reportTypeError(ParserRuleContext source, OFPType expected, OFPType identified) {
        String expectedType = getTypeAsString(expected);
        String identifiedType = getTypeAsString(identified);
        
        errorHandler.reportError(new Error(source, "Type " + expectedType + " expected identified " + identifiedType));
    }

    private void reportTypeError(ParserRuleContext source, String expected, String identified) {        
        errorHandler.reportError(new Error(source, "Type " + expected + " expected identified " + identified));
    }

    private void reportUnsupportedTypeError(ParserRuleContext source, List<OFPType> expected, OFPType identified) {
        String identifiedType = getTypeAsString(identified);
        errorHandler.reportError(
            new Error(source, "Type " + expected + " expected identified " + identifiedType));
    }

    private void unequalTypesError(ParserRuleContext source, OFPType identifiedA, OFPType identifiedB) {
        String identifiedTypeA = getTypeAsString(identifiedA);
        String identifiedTypeB = getTypeAsString(identifiedB);
        errorHandler.reportError(
            new Error(source, "Unequal types at binary operation detected: " + identifiedTypeA + " and " + identifiedTypeB));
    }
    //#endregion
}

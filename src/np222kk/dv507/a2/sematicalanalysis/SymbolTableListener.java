package np222kk.dv507.a2.sematicalanalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import np222kk.dv507.a2.language.OFPLanguageBaseListener;
import np222kk.dv507.a2.language.OFPLanguageParser.DeclarationContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MainMethodContext;
import np222kk.dv507.a2.language.OFPLanguageParser.OptionalStatementGroupContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ParameterContext;
import np222kk.dv507.a2.language.OFPLanguageParser.StartContext;
import np222kk.dv507.a2.language.OFPLanguageParser.SubMethodContext;
import np222kk.dv507.a2.language.OFPLanguageParser.VariableContext;
import np222kk.dv507.a2.sematicalanalysis.error.Error;
import np222kk.dv507.a2.sematicalanalysis.error.SemanticErrorHandler;
import np222kk.dv507.a2.sematicalanalysis.symbol.FunctionSymbol;
import np222kk.dv507.a2.sematicalanalysis.symbol.ParamSymbol;
import np222kk.dv507.a2.sematicalanalysis.symbol.Symbol;
import np222kk.dv507.a2.sematicalanalysis.symbol.VariableSymbol;

public class SymbolTableListener extends OFPLanguageBaseListener {

    private Scope currentScope;
    private FunctionSymbol currentFunctionSymbol;
    private SemanticErrorHandler errorHandler;
    private int indendents = 0;
    
    ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();

    public SymbolTableListener(SemanticErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public ParseTreeProperty<Scope> getSymbolTable() {
        return this.scopes;
    }

    private void printIndendents() {
        for(int i = 1; i < this.indendents; i++) System.out.print("\t");
    }

    private void createSubScope(ParserRuleContext context) {
        Scope subScope = new Scope(this.currentScope);
        scopes.put(context, subScope);
        this.currentScope = subScope;
        this.indendents++;
    }

    private void leaveCurrentScope() {
        this.currentScope = currentScope.getEnclosingScope();
        this.indendents--;
    }

    @Override
    public void enterStart(StartContext ctx) {
        createSubScope(ctx);
    }

    @Override
    public void enterMainMethod(MainMethodContext ctx) {
        FunctionSymbol functionSymbol = new FunctionSymbol("main", null);
        this.currentScope.define(functionSymbol);
        this.currentFunctionSymbol = functionSymbol;
        
        printIndendents();
        System.out.println("Main Method: main (void)");

        // Enter sub scope after adding the FunctionSymbol
        createSubScope(ctx);
    }

    @Override
    public void exitMainMethod(MainMethodContext ctx) {
        this.currentFunctionSymbol = null;
        leaveCurrentScope();
    }

    @Override
    public void enterSubMethod(SubMethodContext ctx) {
        String name = ctx.NAME().toString();
        String returnType = ctx.getStart().getText();
        FunctionSymbol functionSymbol = new FunctionSymbol(name, OFPType.getTypeFor(returnType));
        if (!this.currentScope.define(functionSymbol)) {
            this.errorHandler.reportError(new Error(ctx, "Duplicate function declaration " + name, ctx.getStart().getLine(), 0));
        }
        this.currentFunctionSymbol = functionSymbol;
        
        printIndendents();
        System.out.println("Function: " + name + " (" + returnType + ")");

        // Enter sub scope after adding the FunctionSymbol
        createSubScope(ctx);
    }

    @Override
    public void exitSubMethod(SubMethodContext ctx) {
        this.currentFunctionSymbol = null;
        leaveCurrentScope();
    }

    @Override
    public void enterParameter(ParameterContext ctx) {
        String type = ctx.type().getChild(0).toString();
        String name = ctx.NAME().toString();
        ParamSymbol parameter = new ParamSymbol(name, OFPType.getTypeFor(type));
        this.currentFunctionSymbol.addParameter(parameter);
        if (!this.currentScope.define(parameter)) {
            this.errorHandler.reportError(new Error(ctx, "Duplicate parameter declaration " + name, ctx.getStart().getLine(), ctx.start.getCharPositionInLine()));
        } else {
            printIndendents();
            System.out.println("Parameter: " + name + " (" + type + ")");
        }
    }

    @Override
    public void enterOptionalStatementGroup(OptionalStatementGroupContext ctx) {
        createSubScope(ctx);
    }
    
    @Override
    public void exitOptionalStatementGroup(OptionalStatementGroupContext ctx) {
        leaveCurrentScope();
    }

    @Override
    public void enterDeclaration(DeclarationContext ctx) {
        String name = ctx.NAME().toString();
        String type = ctx.type().getChild(0).toString();
        if (!this.currentScope.define(new VariableSymbol(name, OFPType.getTypeFor(type)))) {
            this.errorHandler.reportError(new Error(ctx, "Duplicate variable declaration " + name, ctx.getStart().getLine(), ctx.start.getCharPositionInLine()));
        } else {
            printIndendents();
            System.out.println("Variable: " + name + " (" + type + ")");
        }
    }

    @Override
    public void enterVariable(VariableContext ctx) {
        String name = ctx.getText();
        Symbol resolvedSymbol = currentScope.resolve(name);

        // Check if variable is already declared:
        // It is not possible to do this step within the CheckRefListener
        // as it would not recognize i = 1; int i; (wrong order)
        if (resolvedSymbol == null
            || resolvedSymbol instanceof FunctionSymbol
        ) {
            this.errorHandler.reportError(
                new Error(ctx, "Variable " + name + " not declared", ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
        }
    }
     
}

package np222kk.dv507.a2.sematicalanalysis;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import np222kk.dv507.a2.language.OFPLanguageBaseListener;
import np222kk.dv507.a2.language.OFPLanguageParser.AssignmentContext;
import np222kk.dv507.a2.language.OFPLanguageParser.DeclarationContext;
import np222kk.dv507.a2.language.OFPLanguageParser.ExpressionContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MainMethodContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MethodCallContext;
import np222kk.dv507.a2.language.OFPLanguageParser.MethodCallParametersContext;
import np222kk.dv507.a2.language.OFPLanguageParser.OptionalStatementGroupContext;
import np222kk.dv507.a2.language.OFPLanguageParser.StartContext;
import np222kk.dv507.a2.language.OFPLanguageParser.SubMethodContext;
import np222kk.dv507.a2.language.OFPLanguageParser.VariableContext;
import np222kk.dv507.a2.sematicalanalysis.error.Error;
import np222kk.dv507.a2.sematicalanalysis.error.SemanticErrorHandler;
import np222kk.dv507.a2.sematicalanalysis.symbol.FunctionSymbol;
import np222kk.dv507.a2.sematicalanalysis.symbol.Symbol;
import np222kk.dv507.a2.sematicalanalysis.symbol.VariableSymbol;

public class CheckRefListener extends OFPLanguageBaseListener {

    private ParseTreeProperty<Scope> scopes;
    private Scope currentScope;
    private Scope functionScope;
    private SemanticErrorHandler errorHandler; 

    private Map<Scope, Set<String>> scopeVariableInitializations;

    public CheckRefListener(ParseTreeProperty<Scope> scopes, SemanticErrorHandler errorHandler) {
        this.scopes = scopes;
        this.errorHandler = errorHandler;
        this.scopeVariableInitializations = new LinkedHashMap<Scope, Set<String>>();
    }

    private void enterScope(ParseTree ctx) {
        currentScope = scopes.get(ctx);
    }

    private void exitScope() {
        currentScope = currentScope.getEnclosingScope();
    }

    private void setInitialized(String variableName) {
        Set<String> initVars = this.scopeVariableInitializations.get(currentScope);
        if (initVars == null) {
            initVars = new HashSet<String>();
            this.scopeVariableInitializations.put(currentScope, initVars);
        }

        initVars.add(variableName);
    }

    private boolean isInitialized(String variableName, Scope scope) {
        if (scope == null) return false;        
        
        Set<String> initVars = this.scopeVariableInitializations.get(scope);
        if (initVars != null && initVars.contains(variableName)) {
            return true;
        } else {
            return isInitialized(variableName, scope.getEnclosingScope());
        }
    }


    @Override
    public void enterStart(StartContext ctx) {
        enterScope(ctx);
        functionScope = scopes.get(ctx);
    }

    @Override
    public void enterMainMethod(MainMethodContext ctx) {
        enterScope(ctx);
    }

    @Override
    public void exitMainMethod(MainMethodContext ctx) {
        exitScope();
    }

    @Override
    public void enterSubMethod(SubMethodContext ctx) {
        enterScope(ctx);
    }

    @Override
    public void exitSubMethod(SubMethodContext ctx) {
        exitScope();
    }

    @Override
    public void enterOptionalStatementGroup(OptionalStatementGroupContext ctx) {
        enterScope(ctx);
    }

    @Override
    public void exitOptionalStatementGroup(OptionalStatementGroupContext ctx) {
        exitScope();
    }

    @Override
    public void enterDeclaration(DeclarationContext ctx) {
        String name = ctx.NAME().toString();

        if (ctx.assignmentRight() != null) {
            setInitialized(name);
        }
    }

    @Override
    public void enterAssignment(AssignmentContext ctx) {
        VariableContext variableCtx;

        // Check if there is a variable assignment and no array access
        if (ctx.variable() != null) {
            variableCtx = ctx.variable();
            
            String variableName = variableCtx.NAME().toString();
            Symbol symbol = this.currentScope.resolve(variableName);
            if (symbol instanceof VariableSymbol) {
                if (symbol != null) {
                    setInitialized(variableName);
                }
            }
        } 
    }

    @Override
    public void enterVariable(VariableContext ctx) {
        String name = ctx.getText();

        Symbol resolvedSymbol = currentScope.resolve(name);

        // Check declaration
        if (resolvedSymbol == null 
            || resolvedSymbol instanceof FunctionSymbol
        ) {
            this.errorHandler.reportError(
                new Error(ctx, "Variable " + name + " not declared", ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
            return;
        }

        // Check initialization
        // This method is also called when the variable is on the left side of an assignment 
        // and therefore in the state of inizializing. This is handled in the assignment and
        // further up in the tree. Therefore this case can be ignored, as the initialization
        // is active.
        if (resolvedSymbol instanceof VariableSymbol
            && !isInitialized(name, currentScope)) {
                this.errorHandler.reportError(
                new Error(ctx, "Variable " + name + " not initialized", ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
            return;
        }
           
    }

    @Override
    public void enterMethodCall(MethodCallContext ctx) {
        String name = ctx.NAME().getText();
        Symbol resolvedSymbol = functionScope.resolve(name);

        // Check if method exists
        if (resolvedSymbol == null
            || !(resolvedSymbol instanceof FunctionSymbol)
        ) {

            this.errorHandler.reportError(
                new Error(ctx, "Methodname " + name + " not declared", ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
            return;
        }

        MethodCallParametersContext parametersContext = ctx.methodCallParameters();

        // Check if parameters exists and necessary
        if (parametersContext == null) {
            if (((FunctionSymbol) resolvedSymbol).hasParameters()) {
                this.errorHandler.reportError(
                    new Error(ctx, "Missing method parameters", ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
                return;
            }
        } else {
            // Check for number of method parameters
            int parameterCounter = 0;
            int expectedParameterCount = ((FunctionSymbol) resolvedSymbol).getParameterCount();
            for (int i = 0; i < parametersContext.getChildCount(); i++) {
                if (parametersContext.getChild(i) instanceof ExpressionContext) {
                    parameterCounter++;
                }
            }
            if (parameterCounter != expectedParameterCount) {
                String msg = "Insufficient number of parameters given " + parameterCounter + " expected " + expectedParameterCount;
                this.errorHandler.reportError(
                    new Error(ctx, msg, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
                return;
            }
        }



    }
    
}

package np222kk.dv507.a2.pythongeneration;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import np222kk.dv507.a2.language.OFPLanguageBaseListener;
import np222kk.dv507.a2.language.OFPLanguageParser;
import np222kk.dv507.a2.language.OFPLanguageParser.MainMethodContext;
import np222kk.dv507.a2.sematicalanalysis.Scope;
import np222kk.dv507.a2.sematicalanalysis.symbol.FunctionSymbol;
import np222kk.dv507.a2.sematicalanalysis.symbol.ParamSymbol;

public class PythonGenerator extends OFPLanguageBaseListener{

    private ParseTreeProperty<Scope> scopes;
    private Scope currentScope;
    private Scope functionScope;
    private String pythonCode = "";
    private int indendents = -1;
    
    public PythonGenerator(ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
        // Not a working and used class right now.
        // Just a placeholder for next assignment
    }

    public String getCode(){
        return this.pythonCode;
    }

    private void enterScope(ParseTree ctx) {
        currentScope = scopes.get(ctx);
    }

    private void exitScope() {
        currentScope = currentScope.getEnclosingScope();
    }

    private void extendPythonCode(String newPart){
        pythonCode = pythonCode + "\n" + getEngage() + newPart;
        System.out.print(pythonCode);
    }

    private String getEngage(){
        String engage = "";
        for(int i = 0; i < indendents;i++){
            engage += "\t";
        }
        return engage;
    }

    @Override public void enterStart(OFPLanguageParser.StartContext ctx) { 
        enterScope(ctx);
        functionScope = scopes.get(ctx);
    }

	@Override public void exitStart(OFPLanguageParser.StartContext ctx) { 
        exitScope();
    }

    @Override
    public void enterMainMethod(MainMethodContext ctx) {
        enterScope(ctx); 
        indendents = 1;
        extendPythonCode("if __name__ == '__main__':");
    }

    @Override
    public void exitMainMethod(MainMethodContext ctx) {
        exitScope();
        indendents = 0;
    }

    @Override 
    public void enterSubMethod(OFPLanguageParser.SubMethodContext ctx) { 
        enterScope(ctx);
        String funcName = ctx.NAME().getText();
        FunctionSymbol func = (FunctionSymbol) functionScope.resolve(funcName);
        List<ParamSymbol> paramList = func.getParameters();
        String parameter ="";
        for(int i = 0; i < paramList.size();i++){
            if(parameter == ""){
                parameter += paramList.get(i);
            }else{
                parameter += ","+paramList.get(i);
            }
        }
        extendPythonCode("def" + ctx.NAME().getText() + "(" + parameter + "):");
        indendents = 1; 
    }

    @Override 
    public void exitSubMethod(OFPLanguageParser.SubMethodContext ctx) { 
        exitScope();
        indendents = 0;
    }

    @Override 
    public void enterIfStatement(OFPLanguageParser.IfStatementContext ctx) { 
        enterScope(ctx);
        extendPythonCode("if");
        indendents += 1;
    }
	
	@Override 
    public void exitIfStatement(OFPLanguageParser.IfStatementContext ctx) { 
        exitScope();
    }

    
    
}

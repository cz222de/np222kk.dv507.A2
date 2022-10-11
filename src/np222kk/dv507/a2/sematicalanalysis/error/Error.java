package np222kk.dv507.a2.sematicalanalysis.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class Error {
    
    private ParserRuleContext source;
    private String msg;
    private int line;
    private int posInLine;

    public Error(ParserRuleContext source, String msg, int line, int posInLine) {
        this.source = source;
        this.msg = msg;
        this.line = line;
        this.posInLine = posInLine;
    }

    public Error(ParserRuleContext source, String msg) {
        this.source = source;
        this.msg = msg;
        this.line = source.getStart().getLine();
        this.posInLine = source.getStart().getCharPositionInLine();
    }

    @Override
    public String toString() {
        String result = this.msg + " in line " + this.line + " position " + this.posInLine;
        return result;
    }

}

package np222kk.dv507.a2.sematicalanalysis.print;

import org.antlr.v4.runtime.ParserRuleContext;

import np222kk.dv507.a2.language.OFPLanguageBaseListener;

public class PrintListener extends OFPLanguageBaseListener{
    private int indendents = -1;

    private void printIndendents() {
        for(int i = 0; i < this.indendents; i++) {
            System.out.print("\t");
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        indendents++;
        printIndendents();
        String nodeName = ctx.getClass().getSimpleName();
        System.out.println(nodeName);
    }

    @Override
    public void exitEveryRule(ParserRuleContext arg0) {
        indendents--;
    }
}

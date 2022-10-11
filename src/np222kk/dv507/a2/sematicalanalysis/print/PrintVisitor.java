package np222kk.dv507.a2.sematicalanalysis.print;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;


import np222kk.dv507.a2.language.OFPLanguageBaseVisitor;

public class PrintVisitor extends OFPLanguageBaseVisitor<Object> {

    private int indendents = 0;

    private void printIndendents() {
        for(int i = 0; i < this.indendents; i++) {
            System.out.print("\t");
        }
    }


    public ParseTree visitAllChildren(ParseTree node) {
        indendents++;
        printIndendents();
        System.out.println(node.getClass().getSimpleName()+": "+node.getChildCount() + " childs");
        for(int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            if (child instanceof TerminalNode) {
                visitTerminal((TerminalNode) child);
            } else {
                visit(child);
            }
        }
        indendents--;
        return node;
    }

    public Object visit(ParseTree node) {
        return visitAllChildren(node);
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        printIndendents();
        System.out.println("\t"+node.getClass().getSimpleName()+": "+node.getText());
        return node;
    }
    
}

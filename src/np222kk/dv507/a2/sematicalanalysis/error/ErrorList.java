package np222kk.dv507.a2.sematicalanalysis.error;

import java.util.List;

public interface ErrorList {
    
    default public int getErrorCount() {
        return this.getErrors().size();
    }

    default public boolean hasErrors() {
        return this.getErrorCount() > 0;
    }

    default public void printErrors() {
        for (Error semanticError : this.getErrors()) {
            System.out.println("\u001B[31mERROR\u001B[0m: " + semanticError.toString());
        }
    }

    List<Error> getErrors();
}

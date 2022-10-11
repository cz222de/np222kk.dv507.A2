package np222kk.dv507.a2.sematicalanalysis.error;

import java.util.ArrayList;
import java.util.List;

public class SemanticErrorHandler implements ErrorList {
    
    List<Error> errors = new ArrayList<Error>();

    public void reportError(Error error) {
        errors.add(error);
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }

}

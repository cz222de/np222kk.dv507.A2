package np222kk.dv507.a2.sematicalanalysis.symbol;

import java.util.ArrayList;
import java.util.List;

import np222kk.dv507.a2.sematicalanalysis.OFPType;

public class FunctionSymbol extends Symbol {

    private List<ParamSymbol> parameters;

    public FunctionSymbol(String name, OFPType type) {
        super(name, type);
        
        this.parameters = new ArrayList<ParamSymbol>();
    }

    public void addParameter(ParamSymbol paramSymbol) {
        this.parameters.add(paramSymbol);
    }

    public List<ParamSymbol> getParameters() {
        return this.parameters;
    }

    public ParamSymbol getParameter(int index) {
        if (index > this.parameters.size()) {
            return null;
        }
        
        return this.parameters.get(index);
    }

    public int getParameterCount() {
        return this.parameters.size();
    }

    public boolean hasParameters() {
        return getParameterCount() > 0;
    }

}

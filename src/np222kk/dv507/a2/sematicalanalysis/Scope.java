package np222kk.dv507.a2.sematicalanalysis;

import java.util.LinkedHashMap;
import java.util.Map;

import np222kk.dv507.a2.sematicalanalysis.symbol.Symbol;

public class Scope {
    
    private Scope enclosingScope;
    private Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

    public Scope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    public Scope getEnclosingScope() {
        return this.enclosingScope;
    }

    public boolean define(Symbol sym) {
        if(symbols.keySet().contains(sym.getName())){
            return false;
        }
        symbols.put(sym.getName(), sym);
        return true;
    }

    public Symbol resolve(String name) {
        Symbol result = symbols.get(name);
        if (result == null) {
            if (enclosingScope != null) {
                result = enclosingScope.resolve(name);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (String key : this.symbols.keySet()) {
            result+= "(" + key + ": " + this.symbols.get(key).getType().toString() + ")";
        }
        return result;
    }
}

package np222kk.dv507.a2.sematicalanalysis.symbol;

import np222kk.dv507.a2.sematicalanalysis.OFPType;

public abstract class Symbol {

    private String name;
    private OFPType type;

    public Symbol(String name, OFPType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public OFPType getType() {
        return type;
    }

}

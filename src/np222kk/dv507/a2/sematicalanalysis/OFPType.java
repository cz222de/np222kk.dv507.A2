package np222kk.dv507.a2.sematicalanalysis;

import java.util.ArrayList;
import java.util.List;

public class OFPType {
    public static final OFPType intType = new OFPType("int");
    public static final OFPType intArrayType = new OFPType("int[]");
    public static final OFPType floatType = new OFPType("float");
    public static final OFPType floatArrayType = new OFPType("float[]");
    public static final OFPType charType = new OFPType("char");
    public static final OFPType charArrayType = new OFPType("char[]");
    public static final OFPType stringType = new OFPType("string");
    // public static final OFPType argsType = new OFPType("string[]");
    public static final OFPType booleanType = new OFPType("bool");

    private final String name;

    private OFPType(String type) {
        this.name = type;
    }

    public static OFPType getTypeFor(String type) {
        switch (type) {
            case "int":
                return intType;
            case "int[]":
                return intArrayType;
            case "float":
                return floatType;
            case "float[]":
                return floatArrayType;
            case "char":
                return charType;
            case "char[]":
                return charArrayType;
            case "string":
                return stringType;
            // case "string[]":
            //     return argsType;
            case "bool":
                return booleanType;
            default:
                return null;
        }
    }

    public static boolean isArrayType(OFPType type) {
        if (type == intArrayType
            || type == floatArrayType
            || type == charArrayType
            // || type == argsType
            ) {
            return true;
        } else {
            return false;
        }
    }

    public static OFPType getElementTypeForArrayType(OFPType type) {
        if (type == intArrayType) {
            return intType;
        } else if (type == floatArrayType) {
            return floatType;
        } else if (type == charArrayType) {
            return charType;
        // } else if (type == argsType) {
        //     return stringType;
        } else if (type == stringType) {
            return charType;
        } else {
            return null;
        }
    }

    public static OFPType getArrayTypeForElementType(OFPType type) {
        if (type == intType) {
            return intArrayType;
        } else if (type == floatType) {
            return floatArrayType;
        } else if (type == charType) {
            return charArrayType;
        // } else if (type == stringType) {
        //     return argsType;
        } else {
            return null;
        }
    }

    public static List<OFPType> getBasicTypes() {
        List<OFPType> types = new ArrayList<OFPType>();
        types.add(intType);
        types.add(floatType);
        types.add(charType);
        types.add(stringType);
        types.add(booleanType);

        return types;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }

}

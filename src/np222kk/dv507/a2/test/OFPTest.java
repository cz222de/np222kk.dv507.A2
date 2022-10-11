package np222kk.dv507.a2.test;

import np222kk.dv507.a2.language.OFPLanguageParser;
import np222kk.dv507.a2.sematicalanalysis.SemanticalAnalysis;
import np222kk.dv507.a2.syntaxanalysis.LexerParser;

public class OFPTest {
    
    String fileName;
    boolean failureExpected;
    boolean result;

    public OFPTest(String fileName, boolean failureExpected) {
        this.fileName = fileName;
        this.failureExpected = failureExpected;
    }

    public boolean runTest() {

        boolean errorIdentified = false;

        LexerParser lexerParser = new LexerParser(this.fileName);
        OFPLanguageParser.StartContext root = lexerParser.lexicalAndSyntaxAnalysis(false);
        if (lexerParser.hasErrors()) {
            errorIdentified = true;
        } else {

            // Start semantical analysis
            SemanticalAnalysis semanticalAnalysis = new SemanticalAnalysis(root);
            errorIdentified = !semanticalAnalysis.run(false);
        }

        if (failureExpected) {
            this.result = errorIdentified;
            return errorIdentified;
        } else {
            this.result = ! errorIdentified;
            return !errorIdentified; // invert result as this test is successful when errors has been detected
        }

    }

    public boolean getResult() {
        return this.getResult();
    }

}

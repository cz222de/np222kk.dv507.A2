package np222kk.dv507.a2.syntaxanalysis;


import java.io.IOException;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import np222kk.dv507.a2.language.OFPLanguageLexer;
import np222kk.dv507.a2.language.OFPLanguageParser;
import np222kk.dv507.a2.language.OFPLanguageParser.StartContext;
import np222kk.dv507.a2.sematicalanalysis.error.OFPErrorListener;

public class LexerParser {
    
    private String inputPath;
    private OFPErrorListener ofpErrorListener;
    
    public LexerParser(String inputPath) {
        this.inputPath = inputPath;
        ofpErrorListener = new OFPErrorListener();
    }

    public StartContext lexicalAndSyntaxAnalysis(boolean systemExit) {
		OFPLanguageParser parser = null;
		OFPLanguageParser.StartContext root = null;
		
		// Read and parse the input program while keeping track of parsing errors
		// ConsoleErrorListener consoleErrorListener = new ConsoleErrorListener();
		try {
			CharStream inputStream = CharStreams.fromFileName(inputPath);
			OFPLanguageLexer lexer = new OFPLanguageLexer(inputStream);
			parser = new OFPLanguageParser(new BufferedTokenStream(lexer));
			// parser.addErrorListener(consoleErrorListener);
			parser.removeErrorListeners();
			parser.addErrorListener(ofpErrorListener);
			root = parser.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (ofpErrorListener.hasErrors()) {
			System.out.println("Errors detected during lexiacal analysis and parsing (" + ofpErrorListener.getErrorCount() + " Errors):");
			ofpErrorListener.printErrors();
            if (systemExit) {
                System.exit(-1);
            }
		}

		return root;
	}

    public boolean hasErrors() {
        return this.ofpErrorListener.hasErrors();
    }
}

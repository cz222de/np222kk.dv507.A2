package np222kk.dv507.a2;


import np222kk.dv507.a2.language.OFPLanguageParser;
import np222kk.dv507.a2.syntaxanalysis.LexerParser;
import np222kk.dv507.a2.sematicalanalysis.SemanticalAnalysis;

public class App {

    public static void main(String[] args) throws Exception {
		
		//String inputPath = "C:\Users\zwick\Documents\Seafile\Seafile\Meine Bibliothek\05_Uni\Master\Semester Växjö\Code Transformation\Abgaben\np222kk.dv507.A2\np222kk.dv507.A2\src\np222kk\dv507\a2\test\cases\positive\test_alot.ofp";
		String inputPath = "/Users/zwick/Documents/Seafile/Seafile/Meine Bibliothek/05_Uni/Master/Semester Växjö/Code Transformation/Abgaben/np222kk.dv507.A2/np222kk.dv507.A2/src/np222kk/dv507/a2/test/cases/input.ofp";
		
		if (args.length >= 1) {
			if (inputPath.endsWith(".ofp")) {
				inputPath = args[0];
			} else {
				System.err.println("No .ofp file provided!");
				System.exit(1);
			}
		} 
		
		// E.g., the user could eventually start this program from the command line as
		// "java -cp lib/*:bin Assignment2StarterCode tests-valid/arithmetic.ofp"
		// and the input path would thus be "tests-valid/arithmetic.ofp"
		
		// Parse the input program
		System.out.println("\nParsing started for " + inputPath);
		
		/*
		 * 		Lexer and Parser
		 */
		LexerParser lexerParser = new LexerParser(inputPath);
		OFPLanguageParser.StartContext root = lexerParser.lexicalAndSyntaxAnalysis(true);

		// Optionally, display the parse tree for the input program:
		// Trees.inspect(root, parser);

		/*
		 * 		Semantical analysis
		 */
		SemanticalAnalysis semanticalAnalysis = new SemanticalAnalysis(root);
		//semanticalAnalysis.printTreeListener();
		//semanticalAnalysis.printTreeVisitor();
		semanticalAnalysis.run(true);


		// After this stage, the input program is expected to be valid!

		/*
		 * 		Python Generation
		 */
		semanticalAnalysis.createPythonCode();
		
	}


}

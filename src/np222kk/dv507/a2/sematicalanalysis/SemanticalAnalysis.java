package np222kk.dv507.a2.sematicalanalysis;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import np222kk.dv507.a2.language.OFPLanguageParser.StartContext;
import np222kk.dv507.a2.pythongeneration.PythonGenerator;
import np222kk.dv507.a2.sematicalanalysis.error.SemanticErrorHandler;
import np222kk.dv507.a2.sematicalanalysis.print.PrintListener;
import np222kk.dv507.a2.sematicalanalysis.print.PrintVisitor;

public class SemanticalAnalysis {
    
    StartContext root;
    SemanticErrorHandler semanticErrors;
    ParseTreeProperty<Scope> symbolTable = null;
    String pythonCode = "";

    public SemanticalAnalysis(StartContext root) {
        this.root = root;
        this.semanticErrors = new SemanticErrorHandler();
    }

    /**
     * Execute the semantical analysis
     * @param systemExit
     * @return status
     */
    public boolean run(boolean systemExit) {

        // Go to the next step only when the previous step was successful
        // This prevents crashing the program. e.g. the type check expects
        // that the number of parameters is correct. This is tested during
        // the reference checking step.
        if (symbolTableConstruction()) {
            if (checkReferencesDeclarationInitialization()) {
                if (checkTypes()) {
                    return true;
                }
            }
        }

        if (systemExit) {
            System.exit(-1);
        }

        return false;
    }

    /**
     * Print the Parse Tree with a Listener
     */
    public void printTreeListener() {
        ParseTreeWalker printWalker = new ParseTreeWalker();
        PrintListener printListener = new PrintListener();
        System.out.println("------------------------------------------");
        System.out.println("PrintListener Output");
        System.out.println("------------------------------------------");
        printWalker.walk(printListener, root);
        System.out.println("------------------------------------------");
    }

    /**
     * Print the Parse Tree with a Visitor
     */
    public void printTreeVisitor() {
        PrintVisitor printVisitor = new PrintVisitor();
        System.out.println("------------------------------------------");
        System.out.println("PrintVisitor Output");
        System.out.println("------------------------------------------");
        printVisitor.visit(root);
        System.out.println("------------------------------------------");
    }

    /**
     * Step 01 - create the Symbol Table
     * @return status
     */
    private boolean symbolTableConstruction() {
        System.out.println("Create symbol table ... ");
		ParseTreeWalker symbolTableWalker = new ParseTreeWalker();
		SymbolTableListener symbolTableListener = new SymbolTableListener(semanticErrors);
		symbolTableWalker.walk(symbolTableListener, root);
		
		this.symbolTable = symbolTableListener.getSymbolTable();
		
		if (semanticErrors.hasErrors()) {
			System.out.println("Create symbol table \u001B[31mERROR\u001B[0m");
			System.out.println("Errors detected during semantical analysis (symbol table construction) (" + semanticErrors.getErrorCount() + " Errors):");
			semanticErrors.printErrors();
			return false;
		} else {
			System.out.println("Create symbol table \u001B[32mOK\u001B[0m");
            return true;
		}
    }

    /**
     * Step 02 - check references and declarations
     * @return status
     */
    private boolean checkReferencesDeclarationInitialization() {
        System.out.print("Checking variable declarations and initializations ...");
		ParseTreeWalker referenceCheckWalker = new ParseTreeWalker();
		CheckRefListener checkReferenceListener = new CheckRefListener(symbolTable, semanticErrors);
		referenceCheckWalker.walk(checkReferenceListener, root);
		
		if (semanticErrors.hasErrors()) {
			System.out.println("\rChecking variable declarations and initializations \u001B[31mERROR\u001B[0m");
			System.out.println("Errors detected during semantical analysis (declaration and initialization check) (" + semanticErrors.getErrorCount() + " Errors):");
			semanticErrors.printErrors();
			return false;
		} else {
			System.out.println("\rChecking variable declarations and initializations \u001B[32mOK\u001B[0m ");
            return true;
		}
    }

    /**
     * Step 03 - perform Type Checking
     * @return status
     */
    private boolean checkTypes() {
        System.out.print("Checking types ...");
		TypeCheckingVisitor typeCheckVisitor = new TypeCheckingVisitor(symbolTable, semanticErrors);
		typeCheckVisitor.visitStart(root);
		
		if (semanticErrors.hasErrors()) {
			System.out.println("\rChecking types \u001B[31mERROR\u001B[0m");
			System.out.println("Errors detected during semantical analysis (type check) (" + semanticErrors.getErrorCount() + " Errors):");
			semanticErrors.printErrors();
			return false;
		} else {
			System.out.println("\rChecking types \u001B[32mOK\u001B[0m ");
            return true;
		}
    }


    /**
     * Generate PythonCode
     */

    public void createPythonCode(){
        
        ParseTreeWalker pythonGeneratorWalker = new ParseTreeWalker();
		PythonGenerator pythonGenerator = new PythonGenerator(symbolTable);
		pythonGeneratorWalker.walk(pythonGenerator, root);
        pythonCode = pythonGenerator.getCode();
    }

    public void printPythonCode(){
        System.out.print("Generate Python Code ...");
        System.out.println(pythonCode);
    }
}

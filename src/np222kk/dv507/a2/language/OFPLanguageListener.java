// Generated from c:\Users\zwick\Documents\Seafile\Seafile\Meine Bibliothek\05_Uni\Master\Semester Växjö\Code Transformation\Abgaben\np222kk.dv507.A2\np222kk.dv507.A2\src\np222kk\dv507\a2\language\OFPLanguage.g4 by ANTLR 4.9.2

    package np222kk.dv507.a2.language;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OFPLanguageParser}.
 */
public interface OFPLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(OFPLanguageParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(OFPLanguageParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#mainMethod}.
	 * @param ctx the parse tree
	 */
	void enterMainMethod(OFPLanguageParser.MainMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#mainMethod}.
	 * @param ctx the parse tree
	 */
	void exitMainMethod(OFPLanguageParser.MainMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#subMethod}.
	 * @param ctx the parse tree
	 */
	void enterSubMethod(OFPLanguageParser.SubMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#subMethod}.
	 * @param ctx the parse tree
	 */
	void exitSubMethod(OFPLanguageParser.SubMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#methodParameters}.
	 * @param ctx the parse tree
	 */
	void enterMethodParameters(OFPLanguageParser.MethodParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#methodParameters}.
	 * @param ctx the parse tree
	 */
	void exitMethodParameters(OFPLanguageParser.MethodParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(OFPLanguageParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(OFPLanguageParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(OFPLanguageParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(OFPLanguageParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(OFPLanguageParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(OFPLanguageParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#ifMain}.
	 * @param ctx the parse tree
	 */
	void enterIfMain(OFPLanguageParser.IfMainContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#ifMain}.
	 * @param ctx the parse tree
	 */
	void exitIfMain(OFPLanguageParser.IfMainContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#elseIfPart}.
	 * @param ctx the parse tree
	 */
	void enterElseIfPart(OFPLanguageParser.ElseIfPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#elseIfPart}.
	 * @param ctx the parse tree
	 */
	void exitElseIfPart(OFPLanguageParser.ElseIfPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void enterElsePart(OFPLanguageParser.ElsePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void exitElsePart(OFPLanguageParser.ElsePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(OFPLanguageParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(OFPLanguageParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#optionalStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterOptionalStatementGroup(OFPLanguageParser.OptionalStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#optionalStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitOptionalStatementGroup(OFPLanguageParser.OptionalStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#statementGroup}.
	 * @param ctx the parse tree
	 */
	void enterStatementGroup(OFPLanguageParser.StatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#statementGroup}.
	 * @param ctx the parse tree
	 */
	void exitStatementGroup(OFPLanguageParser.StatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(OFPLanguageParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(OFPLanguageParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(OFPLanguageParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(OFPLanguageParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(OFPLanguageParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(OFPLanguageParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(OFPLanguageParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(OFPLanguageParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(OFPLanguageParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(OFPLanguageParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#assignmentRight}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentRight(OFPLanguageParser.AssignmentRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#assignmentRight}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentRight(OFPLanguageParser.AssignmentRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(OFPLanguageParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(OFPLanguageParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#arrayDeclarationSize}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclarationSize(OFPLanguageParser.ArrayDeclarationSizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#arrayDeclarationSize}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclarationSize(OFPLanguageParser.ArrayDeclarationSizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#arrayDeclarationInitialization}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclarationInitialization(OFPLanguageParser.ArrayDeclarationInitializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#arrayDeclarationInitialization}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclarationInitialization(OFPLanguageParser.ArrayDeclarationInitializationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryOpCalc}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOpCalc(OFPLanguageParser.BinaryOpCalcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryOpCalc}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOpCalc(OFPLanguageParser.BinaryOpCalcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessComp}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessComp(OFPLanguageParser.LessCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessComp}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessComp(OFPLanguageParser.LessCompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualsComp}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualsComp(OFPLanguageParser.EqualsCompContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualsComp}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualsComp(OFPLanguageParser.EqualsCompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketsExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketsExpr(OFPLanguageParser.BracketsExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketsExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketsExpr(OFPLanguageParser.BracketsExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinusExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpr(OFPLanguageParser.UnaryMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinusExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpr(OFPLanguageParser.UnaryMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CalcObjectExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCalcObjectExpr(OFPLanguageParser.CalcObjectExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CalcObjectExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCalcObjectExpr(OFPLanguageParser.CalcObjectExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#calculationBrackets}.
	 * @param ctx the parse tree
	 */
	void enterCalculationBrackets(OFPLanguageParser.CalculationBracketsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#calculationBrackets}.
	 * @param ctx the parse tree
	 */
	void exitCalculationBrackets(OFPLanguageParser.CalculationBracketsContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#calcObject}.
	 * @param ctx the parse tree
	 */
	void enterCalcObject(OFPLanguageParser.CalcObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#calcObject}.
	 * @param ctx the parse tree
	 */
	void exitCalcObject(OFPLanguageParser.CalcObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#boolValue}.
	 * @param ctx the parse tree
	 */
	void enterBoolValue(OFPLanguageParser.BoolValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#boolValue}.
	 * @param ctx the parse tree
	 */
	void exitBoolValue(OFPLanguageParser.BoolValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#attributeAccess}.
	 * @param ctx the parse tree
	 */
	void enterAttributeAccess(OFPLanguageParser.AttributeAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#attributeAccess}.
	 * @param ctx the parse tree
	 */
	void exitAttributeAccess(OFPLanguageParser.AttributeAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#variableArrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterVariableArrayAccess(OFPLanguageParser.VariableArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#variableArrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitVariableArrayAccess(OFPLanguageParser.VariableArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#methodArrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterMethodArrayAccess(OFPLanguageParser.MethodArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#methodArrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitMethodArrayAccess(OFPLanguageParser.MethodArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(OFPLanguageParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(OFPLanguageParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(OFPLanguageParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(OFPLanguageParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#methodCallParameters}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallParameters(OFPLanguageParser.MethodCallParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#methodCallParameters}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallParameters(OFPLanguageParser.MethodCallParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPLanguageParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(OFPLanguageParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPLanguageParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(OFPLanguageParser.TypeContext ctx);
}
// Generated from c:\Users\zwick\Documents\Seafile\Seafile\Meine Bibliothek\05_Uni\Master\Semester Växjö\Code Transformation\Abgaben\np222kk.dv507.A2\np222kk.dv507.A2\src\np222kk\dv507\a2\language\OFPLanguage.g4 by ANTLR 4.9.2

    package np222kk.dv507.a2.language;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OFPLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OFPLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(OFPLanguageParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#mainMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainMethod(OFPLanguageParser.MainMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#subMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubMethod(OFPLanguageParser.SubMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#methodParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodParameters(OFPLanguageParser.MethodParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(OFPLanguageParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(OFPLanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(OFPLanguageParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#ifMain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfMain(OFPLanguageParser.IfMainContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#elseIfPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfPart(OFPLanguageParser.ElseIfPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#elsePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElsePart(OFPLanguageParser.ElsePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(OFPLanguageParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#optionalStatementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionalStatementGroup(OFPLanguageParser.OptionalStatementGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#statementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementGroup(OFPLanguageParser.StatementGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(OFPLanguageParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(OFPLanguageParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(OFPLanguageParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(OFPLanguageParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(OFPLanguageParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#assignmentRight}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentRight(OFPLanguageParser.AssignmentRightContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclaration(OFPLanguageParser.ArrayDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#arrayDeclarationSize}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclarationSize(OFPLanguageParser.ArrayDeclarationSizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#arrayDeclarationInitialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclarationInitialization(OFPLanguageParser.ArrayDeclarationInitializationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOpCalc}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOpCalc(OFPLanguageParser.BinaryOpCalcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessComp}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessComp(OFPLanguageParser.LessCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualsComp}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualsComp(OFPLanguageParser.EqualsCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BracketsExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketsExpr(OFPLanguageParser.BracketsExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryMinusExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinusExpr(OFPLanguageParser.UnaryMinusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CalcObjectExpr}
	 * labeled alternative in {@link OFPLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcObjectExpr(OFPLanguageParser.CalcObjectExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#calculationBrackets}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculationBrackets(OFPLanguageParser.CalculationBracketsContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#calcObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcObject(OFPLanguageParser.CalcObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#boolValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolValue(OFPLanguageParser.BoolValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#attributeAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeAccess(OFPLanguageParser.AttributeAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#variableArrayAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableArrayAccess(OFPLanguageParser.VariableArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#methodArrayAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodArrayAccess(OFPLanguageParser.MethodArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(OFPLanguageParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(OFPLanguageParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#methodCallParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallParameters(OFPLanguageParser.MethodCallParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPLanguageParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(OFPLanguageParser.TypeContext ctx);
}
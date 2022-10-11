grammar OFPLanguage;

@header {
    package np222kk.dv507.a2.language;
}

/********************************************
        Grammar specification
 ********************************************/

// *** Methods ***
// Only method declarations are allowed at root level
start: subMethod* mainMethod subMethod*;

// method declaration
mainMethod: 'void' 'main' '(' ')' statementGroup;
subMethod: (type | 'void') NAME '(' methodParameters? ')' statementGroup;
methodParameters: parameter (',' parameter)*;
parameter: type NAME; 


// *** Statements ***
statement: ifStatement
    | whileStatement
    | returnStatement END
    | printStatement END
    | declaration END
    | assignment END
    | methodCall END;

// if and while statements
ifStatement: 'if' ifMain elseIfPart* elsePart?;
ifMain: condition optionalStatementGroup;
elseIfPart: 'else' 'if' condition optionalStatementGroup;
elsePart: 'else' optionalStatementGroup;

whileStatement: 'while' condition optionalStatementGroup;

optionalStatementGroup: statement | statementGroup;
statementGroup: '{' statement* '}';
condition: '(' expression ')';

returnStatement: 'return' (expression|arrayDeclaration);
printStatement: ('print'|'println') '(' expression ')';

declaration: type NAME assignmentRight?;
assignment: (variable | variableArrayAccess) assignmentRight;
assignmentRight: ASSIGN (arrayDeclaration|expression);
arrayDeclaration: arrayDeclarationSize | arrayDeclarationInitialization;
arrayDeclarationSize: 'new' (INT|CHAR|FLOAT) '[' expression ']';
arrayDeclarationInitialization: '{' (expression(',' expression)*)? '}';


// *** Expressions ***
expression: calculationBrackets             #BracketsExpr
    | expression (MULT|DIV) expression      #BinaryOpCalc
    | expression (PLUS|MINUS) expression    #BinaryOpCalc
    | MINUS (calculationBrackets | calcObject) #UnaryMinusExpr
    | expression (LESS|GREATER) expression  #LessComp
    | expression EQUALS expression          #EqualsComp
    | calcObject                            #CalcObjectExpr
    ;

// avoiding -----1 in unary - operator --> only allowed in front of brackets or numbers/variables
calculationBrackets: '(' expression ')';


// *** Basics ***
calcObject: FLOATSPEC | INTSPEC | CHARSPEC | STRINGSPEC | boolValue | attributeAccess | variableArrayAccess | methodArrayAccess | variable | methodCall;
boolValue: TRUE | FALSE;

// Variables and method calls
// It is not possible to call an attribute of an array element (There is no attribute for int, float and char)
attributeAccess: (variable | methodCall) '.' NAME; // enable access to .length attribute
variableArrayAccess: variable '[' expression ']';
methodArrayAccess: methodCall '[' expression ']';

variable: NAME;

methodCall: NAME '(' methodCallParameters? ')';
methodCallParameters: expression (',' expression)*;

// type definitions
type: INTARRAY | FLOATARRAY | CHARARRAY | INT | FLOAT | CHAR | STRING | BOOL;


/********************************************
        Token specification
 ********************************************/

// comparision operators
EQUALS : '==';
LESS : '<';
GREATER : '>';

// assignment operator
ASSIGN  : '=';

// bool
TRUE: 'true';
FALSE: 'false';

// specifications for basic types
// positive specifications for numbers. Negative through - operator
INTSPEC: NULL | (NUMBER(NUMBER|'0')*);
NULL: '0'; // otherwise ANTLR would not recognize 0 as an integer
FLOATSPEC: ZEROFLOATSPEC | ('0' '.' ('0'|NUMBER)* NUMBER ('0'|NUMBER)*) | (NUMBER ('0'|NUMBER)* '.' ('0'|NUMBER)+);
ZEROFLOATSPEC: ('0' '.' '0'+);
STRINGSPEC: '"' (LETTER|'0'|NUMBER|'!'|'.'|','|'?'|ASSIGN|':'|'('|')'|' ')+ '"';
CHARSPEC: '\''  (LETTER|'0'|NUMBER|'!'|'.'|','|'?'|ASSIGN|':'|'('|')'|' ') '\'';

// Standard operators
PLUS    : '+' ;
MINUS   : '-' ;
DIV     : '/' ;
MULT    : '*' ;

// available types
INTARRAY: 'int' ARRAYIDENTIFIER;
FLOATARRAY: 'float' ARRAYIDENTIFIER;
CHARARRAY: 'char' ARRAYIDENTIFIER;
STRING: 'string';
BOOL: 'bool';
INT: 'int';
FLOAT: 'float';
CHAR: 'char';

ARRAYIDENTIFIER: '['']';

// method and variable names
NAME: LETTER+;

// Letters and numbers
LETTER: ('a'..'z'|'A'..'Z');
NUMBER: ('1'..'9');

// other tokens
END: ';' ;

COMMENT: '#' .*?  [\r\n] -> skip;

WHITESPACE : [ \t\r\n]+ -> skip ;

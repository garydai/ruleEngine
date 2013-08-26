---
layout: default
title: 一款开源C语言编译器源码解析

---

##一款开源C语言编译器源码解析
###预编译

###词法分析 lex.c	
token表	 

	//keywords
	TOKEN(TK_AUTO,      "auto")
	TOKEN(TK_EXTERN,    "extern")
	TOKEN(TK_REGISTER,  "register")
	TOKEN(TK_STATIC,    "static")
	TOKEN(TK_TYPEDEF,   "typedef")
	TOKEN(TK_CONST,     "const")
	TOKEN(TK_VOLATILE,  "volatile")
	TOKEN(TK_SIGNED,    "signed")
	TOKEN(TK_UNSIGNED,  "unsigned")
	TOKEN(TK_SHORT,     "short")
	TOKEN(TK_LONG,      "long")
	TOKEN(TK_CHAR,      "char")
	TOKEN(TK_INT,       "int")
	TOKEN(TK_INT64,     "__int64")
	TOKEN(TK_FLOAT,     "float")
	TOKEN(TK_DOUBLE,    "double")
	TOKEN(TK_ENUM,      "enum")
	TOKEN(TK_STRUCT,    "struct")
	TOKEN(TK_UNION,     "union")
	TOKEN(TK_VOID,      "void")
	TOKEN(TK_BREAK,     "break")
	TOKEN(TK_CASE,      "case")
	TOKEN(TK_CONTINUE,  "continue")
	TOKEN(TK_DEFAULT,   "default")
	TOKEN(TK_DO,        "do")
	TOKEN(TK_ELSE,      "else")
	TOKEN(TK_FOR,       "for")
	TOKEN(TK_GOTO,      "goto")
	TOKEN(TK_IF,        "if")
	TOKEN(TK_RETURN,    "return")
	TOKEN(TK_SWITCH,    "switch")
	TOKEN(TK_WHILE,     "while")
	TOKEN(TK_SIZEOF,    "sizeof")
	
	//identifier
	TOKEN(TK_ID,        "ID")
	
	//constant
	TOKEN(TK_INTCONST,     "int")
	TOKEN(TK_UINTCONST,    "unsigned int")
	TOKEN(TK_LONGCONST,    "long")
	TOKEN(TK_ULONGCONST,   "unsigned long")
	TOKEN(TK_LLONGCONST,   "long long")
	TOKEN(TK_ULLONGCONST,  "unsigned long long")
	TOKEN(TK_FLOATCONST,   "float")
	TOKEN(TK_DOUBLECONST,  "double")
	TOKEN(TK_LDOUBLECONST, "long double")
	TOKEN(TK_STRING,       "STR")
	TOKEN(TK_WIDESTRING,   "WSTR")
	
	//operators
	TOKEN(TK_COMMA,         ",")
	TOKEN(TK_QUESTION,      "?")
	TOKEN(TK_COLON,         ":")
	TOKEN(TK_ASSIGN,        "=")
	TOKEN(TK_BITOR_ASSIGN,  "|=")
	TOKEN(TK_BITXOR_ASSIGN, "^=")
	TOKEN(TK_BITAND_ASSIGN, "&=")
	TOKEN(TK_LSHIFT_ASSIGN, "<<=")
	TOKEN(TK_RSHIFT_ASSIGN, ">>=")
	TOKEN(TK_ADD_ASSIGN,    "+=")
	TOKEN(TK_SUB_ASSIGN,    "-=")
	TOKEN(TK_MUL_ASSIGN,    "*=")
	TOKEN(TK_DIV_ASSIGN,    "/=")
	TOKEN(TK_MOD_ASSIGN,    "%=")
	TOKEN(TK_OR,            "||")
	TOKEN(TK_AND,           "&&")
	TOKEN(TK_BITOR,         "|")
	TOKEN(TK_BITXOR,        "^")
	TOKEN(TK_BITAND,        "&")
	TOKEN(TK_EQUAL,         "==")
	TOKEN(TK_UNEQUAL,       "!=")
	TOKEN(TK_GREAT,         ">")
	TOKEN(TK_LESS,          "<")
	TOKEN(TK_GREAT_EQ,      ">=")
	TOKEN(TK_LESS_EQ,       "<=")
	TOKEN(TK_LSHIFT,        "<<")
	TOKEN(TK_RSHIFT,        ">>")
	TOKEN(TK_ADD,           "+")
	TOKEN(TK_SUB,           "-")
	TOKEN(TK_MUL,           "*")
	TOKEN(TK_DIV,           "/")
	TOKEN(TK_MOD,           "%")
	TOKEN(TK_INC,           "++")
	TOKEN(TK_DEC,           "--")
	TOKEN(TK_NOT,           "!")
	TOKEN(TK_COMP,          "~")
	TOKEN(TK_DOT,           ".")
	TOKEN(TK_POINTER,       "->")
	TOKEN(TK_LPAREN,        "(")
	TOKEN(TK_RPAREN,        ")")
	TOKEN(TK_LBRACKET,      "[")
	TOKEN(TK_RBRACKET,      "]")
	
	//punctuators
	TOKEN(TK_LBRACE,        "{")
	TOKEN(TK_RBRACE,        "}")
	TOKEN(TK_SEMICOLON,     ";")
	TOKEN(TK_ELLIPSE,       "...")
	TOKEN(TK_POUND,         "#")
	TOKEN(TK_NEWLINE,       "\n")
	
	TOKEN(TK_END,           "EOF")

各种处理字符的函数scanxxx，组成函数指针数组。 

	static Scanner        Scanners[256]; 

得到每个token	

	#define NEXT_TOKEN  CurrentToken = GetNextToken();


###语法解析
语法解析与词法分析一起执行，取一个token然后解析。 

将整个cpp，解析成一个语法树。 

	Decl.c
	transUnit = ParseTranslationUnit(file);

####语法解析程序结构

	ParseTranslationUnit

		ParseExternalDeclaration//解析外部声明

			ParseCommonHeader//各种声明的共同部分

				ParseDeclarationSpecifiers//处理类型限定符(const)、说明符(int)、存储地说明符(auto)

				ParseInitDeclarator

			ParseCompoundStatement//如果是函数定义，解析函数的实现


####语法树
	typedef struct astNode
	{
		AST_NODE_COMMON
	} *AstNode;

	ast节点结构
	#define AST_NODE_COMMON   \
	    int kind;             \节点类型
	    struct astNode *next; \
	    struct coord coord;

	节点类型
	enum nodeKind 
	{ 
		NK_TranslationUnit,     NK_Function,           NK_Declaration,
		NK_TypeName,            NK_Specifiers,         NK_Token,				
		NK_TypedefName,         NK_EnumSpecifier,      NK_Enumerator,			
		NK_StructSpecifier,     NK_UnionSpecifier,     NK_StructDeclaration,	
		NK_StructDeclarator,    NK_PointerDeclarator,  NK_ArrayDeclarator,		
		NK_FunctionDeclarator,  NK_ParameterTypeList,  NK_ParameterDeclaration,
		NK_NameDeclarator,      NK_InitDeclarator,     NK_Initializer,
		
		NK_Expression,
	
		NK_ExpressionStatement, NK_LabelStatement,     NK_CaseStatement,		
		NK_DefaultStatement,    NK_IfStatement,        NK_SwitchStatement,		
		NK_WhileStatement,      NK_DoStatement,        NK_ForStatement,		
		NK_GotoStatement,       NK_BreakStatement,     NK_ContinueStatement,		
		NK_ReturnStatement,     NK_CompoundStatement
	};

	编译单元cpp
	struct astTranslationUnit
	{
		AST_NODE_COMMON
		AstNode extDecls;
	};

###语义分析	

	CheckTranslationUnit(transUnit);

####声明语义检查
####表达式语义检查
####语句语义检查
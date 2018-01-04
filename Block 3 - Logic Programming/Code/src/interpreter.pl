/***
A skeleton for Assignment 3 on PROP HT2017 at DSV/SU.
Peter Idestam-Almquist, 2017-12-14.
***/

/***
Peter Yakob
Lukas Varli
Grupp 13
***/

/*** 
If you choose to use the tokenizer, uncomment the following line of code:

***/
:- [tokenizer].

/***
If you choose to use the tokenizer then call run1/2 as for example:
?- run1('program1.txt','myparsetree1.txt').
***/

run1(InputFile,OutputFile):-
	tokenize(InputFile,Program),
	parse(ParseTree,Program,[]),
	evaluate(ParseTree,[],VariablesOut), 
	output_result(OutputFile,ParseTree,VariablesOut).

/***
If you choose to NOT use the tokenizer then call run2/2, as for example:
?- run2([a,=,1,*,2,+,'(',3,-,4,')',/,5,;],'myparsetree1.txt').
***/
run2(Program,OutputFile):-
	parse(ParseTree,Program,[]),
	evaluate(ParseTree,[],VariablesOut), 
	output_result(OutputFile,ParseTree,VariablesOut).

output_result(OutputFile,ParseTree,Variables):- 
	open(OutputFile,write,OutputStream),
	write(OutputStream,'PARSE TREE:'), 
	nl(OutputStream), 
	writeln_term(OutputStream,0,ParseTree),
	nl(OutputStream), 
	write(OutputStream,'EVALUATION:'), 
	nl(OutputStream), 
	write_list(OutputStream,Variables), 
	close(OutputStream).
	
writeln_term(Stream,Tabs,int(X)):-
	write_tabs(Stream,Tabs), 
	writeln(Stream,int(X)).
writeln_term(Stream,Tabs,ident(X)):-
	write_tabs(Stream,Tabs), 
	writeln(Stream,ident(X)).
writeln_term(Stream,Tabs,Term):-
	functor(Term,_Functor,0), !,
	write_tabs(Stream,Tabs),
	writeln(Stream,Term).
writeln_term(Stream,Tabs1,Term):-
	functor(Term,Functor,Arity),
	write_tabs(Stream,Tabs1),
	writeln(Stream,Functor),
	Tabs2 is Tabs1 + 1,
	writeln_args(Stream,Tabs2,Term,1,Arity).
	
writeln_args(Stream,Tabs,Term,N,N):-
	arg(N,Term,Arg),
	writeln_term(Stream,Tabs,Arg).
writeln_args(Stream,Tabs,Term,N1,M):-
	arg(N1,Term,Arg),
	writeln_term(Stream,Tabs,Arg), 
	N2 is N1 + 1,
	writeln_args(Stream,Tabs,Term,N2,M).
	
write_tabs(_,0).
write_tabs(Stream,Num1):-
	write(Stream,'\t'),
	Num2 is Num1 - 1,
	write_tabs(Stream,Num2).

writeln(Stream,Term):-
	write(Stream,Term), 
	nl(Stream).
	
write_list(_Stream,[]). 
write_list(Stream,[Ident = Value|Vars]):-
	write(Stream,Ident),
	write(Stream,' = '),
	format(Stream,'~1f',Value), 
	nl(Stream), 
	write_list(Stream,Vars).
	
/***
parse(-ParseTree)-->
	A grammar defining our programming language,
	and returning a parse tree.
***/

/* WRITE YOUR CODE FOR THE PARSER HERE */
assignment(assignment(Identifier, assignment_op, Expression, semicolon)) --> ident(Identifier), assignment_op, expression(Expression), semicolon.

ident(ident(Variable)) --> [Variable], {atom(Variable)}.

expression(expression(Term, Operator, Expression)) --> term(Term), expression_operator(Operator), expression(Expression).
expression(expression(Term)) --> term(Term).

term(term(Factor, Operator, Term)) --> factor(Factor), operator(Operator), term(Term).
term(term(Factor)) --> factor(Factor).

factor(factor(Value)) --> value(Value).
factor(factor(Value, Operator, Term)) --> value(Value), operator(Operator), term(Term).
factor(factor(left_paren, Expression, right_paren)) --> left_paren, expression(Expression), right_paren.

value(int(Number)) --> [Number], {integer(Number)}.
value(variable(Variable)) --> [Variable], {atom(Variable)}.

assignment_op --> [=].
expression_operator(add_op) --> [+].
expression_operator(sub_op) --> [-].
operator(mult_op) --> [*].
operator(div_op) --> [/].
left_paren --> ['('].
right_paren --> [')'].
semicolon --> [';'].

parse(ParseTree, Program, []):-
	assignment(ParseTree, Program, []).

/***
evaluate(+ParseTree,+VariablesIn,-VariablesOut):-
	Evaluates a parse-tree and returns the state of the program
	after evaluation as a list of variables and their values in 
	the form [var = value, ...].
***/

/* WRITE YOUR CODE FOR THE EVALUATOR HERE */
%TODO: Läsin output filen som genereras av parsern & räkna ut resultatet av talen
evaluate(ParseTree,[],VariablesOut):-
    write('Hello').
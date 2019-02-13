package jadelex;

public interface Tokenizer {
	jadelex.Yytoken yylex() throws java.io.IOException;

	String yytext();
}
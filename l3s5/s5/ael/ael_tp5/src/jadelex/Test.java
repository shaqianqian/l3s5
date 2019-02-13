package jadelex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class Test {

	public static void main(String arg[]) throws IOException {
		// créer un Yylex qui va prendre ses entrées dans le fichier
		// de nom arg[0]
		Reader input = new BufferedReader(new FileReader(arg[0]));
		Tokenizer yy = new TokenizerV1(input);
		Yytoken token;
		while ((token = yy.yylex()) != null) {
			// System.out.print("["+token.image()+"]<"+token.nom()+">");
			System.out.print(token);
		}
		System.out.println();
	}
}

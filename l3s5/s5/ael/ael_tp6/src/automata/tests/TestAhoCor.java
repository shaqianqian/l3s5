package automata.tests;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import automata.AhoCorasick;
import automata.StateException;

public class TestAhoCor {

	
	public static void main(String[] args) throws StateException, IOException {
		//AhoCorasick a = new AhoCorasick("annie", "honnit", "ni", "nina", "rene", "irene", "rein");
		//AhoCorasick a = new AhoCorasick("potato","theater","tattou","other","at");
		AhoCorasick a = new AhoCorasick("create","at","cry");
		System.out.println(a);
		FileWriter writer = new FileWriter("TestAho.dot");
		a.writeGraphviz(writer);
		writer.close();
		StringWriter bs = new StringWriter();
		//a.writeGraphvizSkeleton(bs);
		a.writeGraphvizLightTransitions(bs);
		//System.out.println(bs);
		System.out.println(a);

	}

}

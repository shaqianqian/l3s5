package automata.tests;

import automata.AutomatonBuilder;
import automata.NDAutomatonWithDeterminisation;
import automata.StateException;
import automata.dotToFile;

public class TestND2 {

	public static void main(String[] args) throws StateException 
	{	
		AutomatonBuilder a = new NDAutomatonWithDeterminisation();
		
		a.addNewState("ini");
        a.addNewState("a");
        a.addNewState("b");
        a.addNewState("c");
        a.addNewState("bc");
        a.addNewState("ca");
		a.setInitial("ini");
		a.setAccepting("a");a.setAccepting("bc");a.setAccepting("ca");
		a.addTransition("ini", 'a', "a");a.addTransition("ini", 'b', "b");a.addTransition("ini", 'c', "c");a.addTransition("b", 'c', "bc");
		a.addTransition("c", 'a', "ca");a.addTransition("ini", 'a', "ini");	a.addTransition("ini", 'b', "ini");a.addTransition("ini", 'c', "ini");
	
				// Déterministation	
		NDAutomatonWithDeterminisation cible = new NDAutomatonWithDeterminisation();
		NDAutomatonWithDeterminisation nd = (NDAutomatonWithDeterminisation) a;
		nd.deterministic(cible);

    
		
		dotToFile.dot(a, "automate-testND2-nondeterministe.dot");
		dotToFile.dot(cible, "automate-testND2-deterministe.dot");
		System.out.println("création automate-testND2-nondeterministe.dot");
		System.out.println("création automate-testND2-deterministe.dot");
	}
}

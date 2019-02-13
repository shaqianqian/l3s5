package automata;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Implémentation d'un automate non déterministe.
 * Version incomplète.
 * 
 * @author Bruno.Bogaert (at) univ-lille1.fr
 *
 */
public class NDAutomatonIncomplete extends AbstractNDAutomaton implements Recognizer, AutomatonBuilder {
	@Override
	/**
	 * Fake implementation : always return false.
	 */
	public boolean accept(String word) {
		//  Ceci n'est pas une implémentation.
		return false;
	}



}

package generic;

import generic.content.Coffee;

public class Q4 {

	public static void main(String[] args) {
		// création de l'objet "mug" 
		// et initialisation des différents types avec cet objet
		ContainerForEdible<Coffee> mug = new ContainerForEdible<Coffee>(15);
		Container<Coffee> ref1 = mug;
		Container<?> ref2 = mug;
		Object ref3 = mug;
	}

}

package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vegetables.Apple;
import vegetables.Carrot;

public class VegetableListChoser {

    // methode chose
    // elle est similaire a celle de ListChoser simplement elle ne fonctionne
    // qu'avec des listes d'objets de type Legume (cf. interface Legume)
    // quel changement apporter a la methode de ListChoser  ?
	
	private final static Scanner scanner = new Scanner(System.in);
	
	public <T> T chose(String message, List<T> list) {
		System.out.println(message);
		int position = (-1);
		while (position < 0 || position > list.size()) 
			position = scanner.nextInt();
		if (position == 0)
			return null;
		else
			return list.get(position);
	}
    
    public static void main(String[] args) {
		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		VegetableListChoser lcl = new VegetableListChoser();

		Carrot chosenCarrot = lcl.chose("which carrot ? ", lCarrots);
		System.out.println("You have chosen : " + chosenCarrot);

		// NE COMPILE PAS
		// Apple chosenApple = lcl.chose("which apple ? ",lApples);


    }
}

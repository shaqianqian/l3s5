package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vegetables.Apple;
import vegetables.Carrot;

public class ListChoser {

	// Definir la methode chose, qui prend en premier parametre
	// un message sous forme de chaene de caracteres et en second une liste.
	// Cette liste est typee mais sans restriction sur les types admis.
	// Cette methode propose de choisir un element de la liste en saisissant
	// sa position dans la liste.
	// L'element choisi est retourne par la methode, null si le choix 0 est
	// fait.
	//
	// ...methode chose...
	//

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
		// JEU DE TEST

		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		ListChoser lc = new ListChoser();

		Carrot chosenCarrot = lc.chose("which carrot ? ", lCarrots);
		System.out.println("you have chosen : " + chosenCarrot);

		Apple chosenApple = lc.chose("which appel? ", lApples);
		System.out.println("you have chosen : " + chosenApple);

		scanner.close();
	}
}

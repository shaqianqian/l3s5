package fil.coo.donjonma;
import java.util.Scanner;

public class Game {
	public boolean start(){
		int k = 0;
		Room r1 = new Room(1, 3);
		Room r2 = new Room(2, 4);
		Room r3 = new Room(3, 5);
		Room r4 = new Room(4, 6);
		boolean a1 = false,a2 = false,a3 = false,a4=false;
		while (k == 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("1 means up\n2 means down\n3 means right\n4 means left");
			System.out.println("Choose a direction to begin your adventure:");
			int direction = sc.nextInt();

			switch (direction) {
			case 1:
				System.out.println("You choose to go straight,good luck");
				a1=r1.addMonster(1, 3);
				k++;
				break;
				
			case 2:
				System.out.println("You choose to go down,good luck");
				a2=r2.addMonster(2, 4);
				k++;
				break;
			case 3:
				System.out.println("You choose to go left,good luck");
				a3=r3.addMonster(3, 5);
				k++;
				break;
			case 4:
				System.out.println("You choose to go right,good luck");
				a4=r4.addMonster(4, 6);
				k++;
				break;
			default:
				System.out.println("enter not right, try again.");

			}
            
		}
		return a1||a2||a3||a4;
	}

}

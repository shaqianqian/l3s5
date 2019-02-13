package fil.coo.donjonma;
import java.util.Scanner;

public class Room {
	private int rnum;
	private int mnum;
	protected int mBlood;

	public Room(int rnum, int mnum) {
		this.rnum = rnum;
		this.mnum = mnum;
	}

	public int random() {
		int ran = (int) (Math.random() * (10 - 1) + 1);
		return ran;
	}

	public boolean addMonster(int rnum, int mnum) {
		int ran = random();
		System.out.println(ran);
		Monster m;
		boolean result;
		int pran = random();
		System.out.println(pran);
		Player player = new Player(pran, pran * 2, pran * 3);
		Character attack = new Character();
		switch (rnum) {
		case 1:
			m = new Monster(ran * 2, ran * mnum);
			break;
		case 2:
			m = new Monster(ran, ran * mnum);
			break;
		case 3:
			m = new Monster(ran * 3, ran * mnum);
			break;
		case 4:
			m = new Monster(ran * 4, ran * mnum);
			break;
		default:
			break;
		}
		System.out.println("you are in room" + rnum + "\n");
		mBlood = ran * mnum * rnum;
		System.out.println("these monster have" + mBlood + " Bourse, do you want to use your items?");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1 means yes,2 means no ");
		int res = scanner.nextInt();
		switch (res) {
		case 1:

			int pBlood = player.transBlood(player.getBlood(), player.getForce(), player.getGold());
			System.out.println("before attack you have " + pBlood + "Blood");
			result = attack.attack(mBlood, pBlood, player);
			if (result) {
				System.out.println("Comngratulations!you win this room,but the exit not appear now,try another room please");
			} else {
				System.out.println("you loose this room,and lose donjon,don't worry, try again");
			}

			break;
		case 2:
			pBlood = player.transBlood(player.getBlood(), player.getForce(), player.getGold());
			System.out.println("before attack you have " + pBlood + "Blood");
			result = attack.attack2(mBlood, pBlood, player);
			if (result) {
				System.out.println("Comngratulations!you win this room,but the exit not appear now,try another room");
			} else {
				System.out.println("you loose,don't worry, try again");
			}

		}
		return player.getisLive();
	}
}

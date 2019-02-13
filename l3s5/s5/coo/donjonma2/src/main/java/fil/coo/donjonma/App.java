package fil.coo.donjonma;

public class App {
	int i = 0;
	static int sum = 0;

	public static void main(String args[]) {
		Game g = new Game();
		boolean status;
		status = g.start();

		while (status == true) {
			sumt(status);
			
			status = g.start();
			if (sum == 4)
				System.out.println("the exit appear,you get out,congratulations,you win donjon!");
			break;
		}
	}

	public static int sumt(boolean status) {
		if (status = true) {
			sum++;
		}
		return sum;
	}

}

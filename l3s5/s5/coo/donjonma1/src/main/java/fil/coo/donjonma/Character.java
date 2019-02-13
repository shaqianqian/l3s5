package fil.coo.donjonma;

public class Character {
	private boolean isLive;
	private int blood;

	boolean islive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public boolean attack(int mBlood, int pBlood, Player p) {

		if (mBlood > pBlood) {
			int power = p.getForce() + p.getGold() + p.getBlood();
			if (power > mBlood) {
				p.setLive(true);
				p.setBlood(power - mBlood);
				p.setGold(0);
				p.setForce(0);

				System.out.println("your force is " + p.getForce());
				System.out.println("your or is " + p.getGold());
			} else {
				p.setLive(false);
			}
		} else {
			p.setLive(true);

			System.out.println("your force is " + p.getForce());
			System.out.println("your or is " + p.getGold());
		}
		return p.getisLive();

	}

	public boolean attack2(int mBlood, int pBlood, Player p) {

		if (mBlood >= pBlood) {
			p.setLive(false);
			System.out.println("your blood is " + p.getBlood());
			System.out.println("your force is " + p.getForce());
			System.out.println("your or is " + p.getGold());

		} else {
			p.setLive(true);
			System.out.println("your blood is " + p.getBlood());
			System.out.println("your force is " + p.getForce());
			System.out.println("your or is " + p.getGold());
		}
		return p.getisLive();

	}

}

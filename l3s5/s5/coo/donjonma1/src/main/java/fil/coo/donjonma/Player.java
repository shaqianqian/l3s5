package fil.coo.donjonma;

public class Player extends Character{
	private boolean isLive;
	private int currentLife;
	private int blood;
	private int force;
	private int gold;

	public boolean getisLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public int getCurrentLife() {
		return currentLife;
	}

	public void setCurrentLife(int currentLife) {
		this.currentLife = currentLife;
	}

	Player(int blood, int force, int gold) {
		this.blood = blood;
		this.force = force;
		this.gold = gold;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public boolean isLive() {
		return isLive;
	}

	public int transBlood(int blood, int gold, int force) {
		int kblood = this.blood + this.gold + this.force;
		return kblood;
	}
}

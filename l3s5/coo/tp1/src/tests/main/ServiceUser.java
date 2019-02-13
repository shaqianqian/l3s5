package tests.main;

public class ServiceUser {
	private int money;
	
	public ServiceUser(int initialMoney) {
		this.money = initialMoney;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void decreaseMoney(int amount) {
		this.money = this.money - amount;
	}
}

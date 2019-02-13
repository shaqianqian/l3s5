package tests.main;

public abstract class Service {

	private int nbUse;
	
	public Service() {
		this.nbUse = 0;
	}
	
	public abstract int cost();
	public abstract void execute();
	
	public int getNumberOfUse() {
		return this.nbUse;
	}
	
	/** The user uses this service. He needs to have enough money.
	 * @param user the user of this service
	 * @throws NotEnoughMoneyException if the user has not enough money to pay this service's cost
	 */
	public final void isUsedBy(ServiceUser user) throws NotEnoughMoneyException {
		if (user.getMoney() < this.cost()) {
			throw new NotEnoughMoneyException ();
		}
		user.decreaseMoney(this.cost());
		this.execute();
		this.nbUse ++;
	}
}

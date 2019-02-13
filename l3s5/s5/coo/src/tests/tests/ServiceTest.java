package tests.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tests.main.NotEnoughMoneyException;
import tests.main.Service;
import tests.main.ServiceUser;

public abstract class ServiceTest {

	protected abstract Service createService(); 
	
	@Test
	public void testIsUsedByWhenOk() throws Exception {
		Service service = this.createService();
		ServiceUser user = new ServiceUser(2* service.cost());
		int moneyBefore = user.getMoney();
		int nbUseBefore = service.getNumberOfUse();
		
		service.isUsedBy(user);
		
		assertEquals(1, service.getNumberOfUse() - nbUseBefore);
		assertEquals(service.cost(), moneyBefore - user.getMoney()); 		
	}

	@Test(expected = NotEnoughMoneyException.class)
	public void testIsUsedByWhenUserHasNotEnoughMoney() throws Exception {
		Service service = this.createService();
		ServiceUser user = new ServiceUser(-1);
		// ou pour imposer un solde négatif :
		// user.decreaseMoney(user.getMoney()-1); 
		service.isUsedBy(user);		
	}
	
	// la création d'un mock "MockService" n'est pas ici nécessaire
	// puisque cette classe de test est abstraite et que ses tests
	// sont exécutés via les sous-classes qui doivent nécessairement
	// définir createService()
}

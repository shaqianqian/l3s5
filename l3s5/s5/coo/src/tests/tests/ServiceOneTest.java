package tests.tests;

import tests.main.Service;
import tests.main.ServiceOne;

public class ServiceOneTest extends ServiceTest {

	protected Service createService() {
		return new ServiceOne();
	}

}

package tests.tests;

import tests.main.Service;
import tests.main.ServiceTwo;

public class ServiceTwoTest extends ServiceTest {

	protected Service createService() {
		return new ServiceTwo();
	}

}

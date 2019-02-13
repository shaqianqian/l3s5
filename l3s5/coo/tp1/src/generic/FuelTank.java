package generic;

import generic.content.Fuel;

/**
 * Containers that accept only Fuel.
 */
public class FuelTank extends Container<Fuel> {

	public FuelTank(int maxVolume) {
		super(maxVolume);
	}

}

package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import vegetables.Apple;
import vegetables.Carrot;
import vegetables.Cauliflower;
import vegetables.Rutabaga;
import vegetables.Vegetable;

public class VegetableTest {

	@Test
	public void Carrot() {
		Vegetable carrot = new Carrot(5);
		assertEquals(true, (carrot instanceof Vegetable));
		assertEquals(5, carrot.getSize());
	}

	@Test
	public void Cauliflower() {
		Vegetable carrot = new Cauliflower(2);
		assertEquals(true, (carrot instanceof Cauliflower));
		assertEquals(2, carrot.getSize());
	}

	@Test
	public void Apple() {
		Vegetable carrot = new Apple(1);
		assertEquals(true, (carrot instanceof Apple));
		assertEquals(1, carrot.getSize());
	}

	@Test
	public void Rutabaga() {
		Vegetable carrot = new Rutabaga(8);
		assertEquals(true, (carrot instanceof Rutabaga));
		assertEquals(8, carrot.getSize());
	}

}

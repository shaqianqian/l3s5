package tests;

import static org.junit.Assert.*;
import generics.Collector;

import org.junit.Test;

import vegetables.Carrot;
import vegetables.Vegetable;

public class CollectorTest {
	
	@Test
	public void Collector_collectAndDrop() {
		Collector<Carrot> collector = new Collector<Carrot>("carrot-collector");
		assertEquals(null, collector.getCarriedObject());
		Carrot c1 = new Carrot(5);
		collector.collect(c1);
		assertEquals(c1, collector.getCarriedObject());
		collector.drop();
		assertEquals(null, collector.getCarriedObject());
	}

	@Test
	public void CollectorDescriptionTest() {
		Collector<Carrot> collector = new Collector<Carrot>("carrot-collector");
		assertEquals(collector.description(), "carrot-collector" + " carries " + "null");
		Carrot c1 = new Carrot(5);
		collector.collect(c1);
		assertEquals("carrot-collector" + " carries " + "["+"Carrot"+", size="+"5"+"]",collector.description());
	}
	
	@Test
	public void CollectorTransferTest() {
		Collector<Carrot> collector = new Collector<Carrot>("carrot-collector");
		Collector<Vegetable> collector2 = new Collector<Vegetable>("vegetable-collector");
		
		Carrot c1 = new Carrot(2);
		collector.collect(c1);
		
		assertEquals(c1, collector.getCarriedObject());
		assertEquals(null, collector2.getCarriedObject());
		
		collector.giveTo(collector2);
		assertEquals(c1, collector2.getCarriedObject());
		assertEquals(null, collector.getCarriedObject());
		

	}
}

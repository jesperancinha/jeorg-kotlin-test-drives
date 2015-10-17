package com.steelzack.coffee.system;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.steelzack.coffee.system.ProgrammerProcessorImpl;
import com.steelzack.coffee.system.objects.Coffee;
import com.steelzack.coffee.system.objects.CoffeeStats;
import com.steelzack.coffee.system.objects.Payment;
import com.steelzack.coffee.system.objects.Programmer;

public class ProgrammerProcessorImplTest {

	private static Random random = new Random();

	@Test
	public void testProcessProgrammers_One() throws Exception {
		List<Programmer> programmers = new ArrayList<>();
		Programmer programmer = new Programmer(Coffee.Cappuccino, Payment.CASH);
		programmers.add(programmer);
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(2750d, result.getAverageCoffee());
		assertEquals(2750, result.getFastestProgrammerTime());
		assertEquals(2750, result.getSlowesProgrammerTime());
		assertEquals(1, result.getCoffeCappuccino());
		assertEquals(0, result.getCoffeeMachiatto());
		assertEquals(0, result.getCoffeExpresso());
	}

	@Test
	public void testProcessProgrammers_Ten() throws Exception {
		final List<Programmer> programmers = new ArrayList<>();
		final Programmer programmer1 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer2 = new Programmer(Coffee.Cappuccino,
				Payment.CREDITCARD);
		final Programmer programmer3 = new Programmer(Coffee.Expresso,
				Payment.CASH);
		final Programmer programmer4 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer5 = new Programmer(Coffee.LatteMachiatto,
				Payment.CASH);
		final Programmer programmer6 = new Programmer(Coffee.LatteMachiatto,
				Payment.CREDITCARD);
		final Programmer programmer7 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer8 = new Programmer(Coffee.Cappuccino,
				Payment.CREDITCARD);
		final Programmer programmer9 = new Programmer(Coffee.Expresso,
				Payment.CASH);
		final Programmer programmer10 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		programmers.add(programmer1);
		programmers.add(programmer2);
		programmers.add(programmer3);
		programmers.add(programmer4);
		programmers.add(programmer5);
		programmers.add(programmer6);
		programmers.add(programmer7);
		programmers.add(programmer8);
		programmers.add(programmer9);
		programmers.add(programmer10);
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(4, result.getCoffeCappuccino());
		assertEquals(2, result.getCoffeeMachiatto());
		assertEquals(4, result.getCoffeExpresso());
		System.out.println(result);
	}

	@Test
	public void testProcessProgrammers_TenSameFastest() throws Exception {
		final List<Programmer> programmers = new ArrayList<>();
		final Programmer programmer1 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer2 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer3 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer4 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer5 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer6 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer7 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer8 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer9 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		final Programmer programmer10 = new Programmer(Coffee.Expresso,
				Payment.CREDITCARD);
		programmers.add(programmer1);
		programmers.add(programmer2);
		programmers.add(programmer3);
		programmers.add(programmer4);
		programmers.add(programmer5);
		programmers.add(programmer6);
		programmers.add(programmer7);
		programmers.add(programmer8);
		programmers.add(programmer9);
		programmers.add(programmer10);
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(0, result.getCoffeCappuccino());
		assertEquals(0, result.getCoffeeMachiatto());
		assertEquals(10, result.getCoffeExpresso());
		assertEquals(2250d, result.getAverageCoffee());
		assertEquals(2000, result.getFastestProgrammerTime());
		assertEquals(2500, result.getSlowesProgrammerTime());
	}

	@Test
	public void testProcessProgrammers_TenSameSlowest() throws Exception {
		final List<Programmer> programmers = new ArrayList<>();
		final Programmer programmer1 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer2 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer3 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer4 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer5 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer6 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer7 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer8 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer9 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		final Programmer programmer10 = new Programmer(Coffee.Cappuccino,
				Payment.CASH);
		programmers.add(programmer1);
		programmers.add(programmer2);
		programmers.add(programmer3);
		programmers.add(programmer4);
		programmers.add(programmer5);
		programmers.add(programmer6);
		programmers.add(programmer7);
		programmers.add(programmer8);
		programmers.add(programmer9);
		programmers.add(programmer10);
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(10, result.getCoffeCappuccino());
		assertEquals(0, result.getCoffeeMachiatto());
		assertEquals(0, result.getCoffeExpresso());
		assertEquals(3125d, result.getAverageCoffee());
		assertEquals(2750, result.getFastestProgrammerTime());
		assertEquals(3500, result.getSlowesProgrammerTime());
	}

	@Test
	public void testProcessProgrammers_200() throws Exception {
		List<Programmer> programmers = new ArrayList<>();
		Coffee[] coffeeValues = Coffee.values();
		Payment[] paymentValues = Payment.values();
		for (int i = 0; i < 200; i++) {
			int iCoffee = random.nextInt(3);
			int iPayment = random.nextInt(2);
			Programmer programmer = new Programmer(coffeeValues[iCoffee],
					paymentValues[iPayment]);
			programmers.add(programmer);
		}
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(200, //
				result.getCoffeCappuccino() + //
						result.getCoffeeMachiatto() + //
						result.getCoffeExpresso());
		assertTrue(result.getAverageCoffee() > 10000
				&& result.getAverageCoffee() < 50000);
		assertTrue(result.getFastestProgrammerTime() < 3000);
		assertTrue(result.getSlowesProgrammerTime() > 10000);
		System.out.println(result);
	}

	@Test
	public void testProcessProgrammers_500() throws Exception {
		List<Programmer> programmers = new ArrayList<>();
		Coffee[] coffeeValues = Coffee.values();
		Payment[] paymentValues = Payment.values();
		for (int i = 0; i < 500; i++) {
			int iCoffee = random.nextInt(3);
			int iPayment = random.nextInt(2);
			Programmer programmer = new Programmer(coffeeValues[iCoffee],
					paymentValues[iPayment]);
			programmers.add(programmer);
		}
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(500, //
				result.getCoffeCappuccino() + //
						result.getCoffeeMachiatto() + //
						result.getCoffeExpresso());
		assertTrue(result.getAverageCoffee() > 20000
				&& result.getAverageCoffee() < 100000);
		assertTrue(result.getFastestProgrammerTime() < 3000);
		assertTrue(result.getSlowesProgrammerTime() > 20000);
		System.out.println(result);
	}

	@Test
	public void testProcessProgrammers_1000() throws Exception {
		List<Programmer> programmers = new ArrayList<>();
		Coffee[] coffeeValues = Coffee.values();
		Payment[] paymentValues = Payment.values();
		for (int i = 0; i < 1000; i++) {
			int iCoffee = random.nextInt(3);
			int iPayment = random.nextInt(2);
			Programmer programmer = new Programmer(coffeeValues[iCoffee],
					paymentValues[iPayment]);
			programmers.add(programmer);
		}
		ProgrammerProcessorImpl processor = new ProgrammerProcessorImpl();
		CoffeeStats result = processor.processProgrammers(programmers);
		assertEquals(1000, //
				result.getCoffeCappuccino() + //
						result.getCoffeeMachiatto() + //
						result.getCoffeExpresso());
		assertTrue(result.getAverageCoffee() > 20000
				&& result.getAverageCoffee() < 200000);
		assertTrue(result.getFastestProgrammerTime() < 3000);
		assertTrue(result.getSlowesProgrammerTime() > 40000);
		System.out.println(result);
	}
}

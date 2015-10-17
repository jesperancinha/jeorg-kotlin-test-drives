package com.steelzack.coffee.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.steelzack.coffee.system.ProgrammerProcessor;
import com.steelzack.coffee.system.objects.CoffeeStats;
import com.steelzack.coffee.system.objects.Programmer;

public class ProgrammerProcessorImpl implements ProgrammerProcessor {

	private static Random random = new Random();
	private static int UNIT_OF_TIME = 250;

	@Override
	public CoffeeStats processProgrammers(List<Programmer> programmers) {
		List<Programmer> subListCoffeePick = getRandomAndRemoveFrom(
				programmers, 10);
		List<Programmer> subListCoffeePay = new ArrayList<Programmer>();
		List<Programmer> subListCoffeeGet = new ArrayList<Programmer>();
		List<Programmer> completePick = new ArrayList<Programmer>();
		List<Programmer> completeGet = new ArrayList<Programmer>();
		List<Programmer> completePay = new ArrayList<Programmer>();
		List<Programmer> completeAll = new ArrayList<Programmer>();
		while (programmers.size() > 0 || subListCoffeePick.size() > 0
				|| subListCoffeePay.size() > 0 || subListCoffeeGet.size() > 0) {

			addOffSet(programmers, UNIT_OF_TIME);
			for (Programmer programmer : subListCoffeePick) {
				programmer.addTimeStamp(UNIT_OF_TIME);
				if (programmer.isDonePick()) {
					completePick.add(programmer);
				}
			}

			for (Programmer programmer : completePick) {
				subListCoffeePick.remove(programmer);
			}

			subListCoffeePick.addAll(getRandomAndRemoveFrom(programmers,
					completePick.size()));


			for (Programmer programmer : subListCoffeePay) {
				programmer.addTimeStamp(UNIT_OF_TIME);
				if (programmer.isDonePay()) {
					completePay.add(programmer);
				}
			}
			

			if (subListCoffeePay.size() < 5) {
				final int nToPay = 5 - subListCoffeePay.size();
				subListCoffeePay.addAll(getRandomAndRemoveFrom(completePick,
						nToPay));
			}
			addOffSet(completePick, UNIT_OF_TIME);

			for (Programmer programmer : completePay) {
				subListCoffeePay.remove(programmer);
			}

			for (Programmer programmer : subListCoffeeGet) {
				programmer.addTimeStamp(UNIT_OF_TIME);
				if (programmer.isDoneGet()) {
					completeGet.add(programmer);
				}
			}

			for (Programmer programmer : completeGet) {
				subListCoffeeGet.remove(programmer);
				completeAll.add(programmer);
			}
			if (subListCoffeeGet.size() < 2) {
				final int nToPay = 2 - subListCoffeeGet.size();
				subListCoffeeGet.addAll(getRandomAndRemoveFrom(completePay,
						nToPay));
			}
			completeGet.clear();
		}

		int coffeSoldCash = 0;
		int coffeSoldCard = 0;
		double averageCoffee = 0;
		int slowestProgrammerTime = 0;
		int fastestProgrammerTime = -1;
		int coffeExpresso = 0;
		int coffeeMachiatto = 0;
		int coffeCappuccino = 0;

		for (Programmer programmer : completeAll) {
			switch (programmer.getPayment()) {
			case CASH:
				coffeSoldCash += 1;
				break;
			case CREDITCARD:
				coffeSoldCard += 1;
				break;
			}

			switch (programmer.getCoffee()) {
			case Cappuccino:
				coffeCappuccino += 1;
				break;
			case Expresso:
				coffeExpresso += 1;
				break;
			case LatteMachiatto:
				coffeeMachiatto += 1;
				break;
			}
			if (fastestProgrammerTime == -1
					|| programmer.getCompleteTime() < fastestProgrammerTime) {
				fastestProgrammerTime = programmer.getCompleteTime();
			}

			if (programmer.getCompleteTime() > slowestProgrammerTime) {
				slowestProgrammerTime = programmer.getCompleteTime();
			}
			averageCoffee += programmer.getCompleteTime();
		}

		averageCoffee = averageCoffee / completeAll.size();
		CoffeeStats stats = new CoffeeStats(coffeSoldCash, //
				coffeSoldCard, //
				coffeExpresso, //
				coffeeMachiatto, //
				coffeCappuccino, //
				averageCoffee, //
				fastestProgrammerTime, //
				slowestProgrammerTime //
		);
		return stats;
	}

	private List<Programmer> getRandomAndRemoveFrom(
			List<Programmer> programmers, int nElements) {
		List<Programmer> subListProgrammers = new ArrayList<Programmer>();
		for (int i = 0; i < nElements && programmers.size() > 0; i++) {
			final int selectedIndex = random.nextInt(programmers.size());
			final Programmer selectedProgrammer = programmers
					.get(selectedIndex);
			subListProgrammers.add(selectedProgrammer);
			programmers.remove(selectedProgrammer);

		}
		return subListProgrammers;
	}

	private void addOffSet(List<Programmer> programmers, int offset) {
		for (Programmer programmer : programmers) {
			programmer.addOffset(offset);
		}
	}
}

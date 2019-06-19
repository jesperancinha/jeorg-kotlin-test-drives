package com.jesperancinha.string.paradigm.original.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RunSpellingCheck {
	public static void main(String[] args) {
		String[] regions = { "Algarve", "minho", " Ribatejo" };
		String[] assignedNumbers = { "5'", "6'", ",2","3'", "7'", ",1" };
		List<RegionImpl> components = new ArrayList<RegionImpl>();
		for (String type : regions) {
			for (String size : assignedNumbers) {
				components.add(new RegionImpl(type, size));
			}
		}

		SpellingCheck check = new SpellingCheck();
		for (String type : regions) {
			check.checkSpelling(type);
		}
		for (String size : assignedNumbers) {
			check.checkSpelling(size);
		}
		List<String> incorrect = new ArrayList<>();
		incorrect.addAll(check.getSpellingDoubleCommaRegions());
		incorrect.addAll(check.getSpellingBeginCapitalRegions());
		incorrect.addAll(check.getSpellingWhiteSpacesRegions());
		System.out.println("Incorrect attributes: " + incorrect);

		RegionSpellingCheck componentCheck = new RegionSpellingCheck();
		for (RegionImpl component : components) {
			componentCheck.checkSpelling(component);
		}
		List<RegionImpl> incorrectRegions = new ArrayList<RegionImpl>();
		incorrectRegions.addAll(componentCheck.getSpellingDoubleCommaRegions());
		incorrectRegions.addAll(componentCheck.getSpellingBeginCapitalRegions());
		incorrectRegions.addAll(componentCheck.getSpellingWhiteSpacesRegions());
		System.out.println("Incorrect components:");
		for (RegionImpl component : incorrectRegions) {
			System.out.println(component.getDescription());
		}

		Set<RegionImpl> set = new HashSet<RegionImpl>();
		set.add(new RegionImpl(regions[1], assignedNumbers[0]));
		set.add(new RegionImpl(regions[1], assignedNumbers[0]));
		set.add(new RegionImpl(regions[1], assignedNumbers[2]));
		System.out.println("Similar components: " + findSimilarRegions(components, set));
	}

	public static Set<RegionImpl> findSimilarRegions(Collection<? extends RegionImpl> componentsA,
			Collection<? extends RegionImpl> componentsB) {
		// TODO To be implemented
		return null;
	}
}

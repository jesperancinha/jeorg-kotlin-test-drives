package com.steelzack.string.paradigm1.expression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
		List<String> incorrect = new ArrayList<String>();
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
		Set<RegionImpl> setToReturn = new HashSet<RegionImpl>();
		setToReturn = similarElementsIteration(componentsA, componentsB, setToReturn);
		setToReturn = similarElementsIteration(componentsB, componentsA, setToReturn);
		System.out.println(Arrays.deepToString(setToReturn.toArray()));
		return setToReturn;
	}

	public static Set<RegionImpl> similarElementsIteration(Collection<? extends RegionImpl> componentsA,
			Collection<? extends RegionImpl> componentsB, Set<RegionImpl> setToReturn) {
		Iterator<? extends RegionImpl> iA = componentsA.iterator();

		while (iA.hasNext()) {
			RegionImpl compareRoot = iA.next();
			Iterator<? extends RegionImpl> iB = componentsB.iterator();
			Field[] rootFields = compareRoot.getClass().getDeclaredFields();
			while (iB.hasNext()) {
				RegionImpl compareRootTo = iB.next();

				Field[] toFields = compareRootTo.getClass().getDeclaredFields();
				if (Arrays.equals(rootFields, toFields)) {
					boolean valuesOk = true;
					try {
						for (Field field : toFields) {
							Field fieldType = RegionImpl.class.getDeclaredField(field.getName());
							fieldType.setAccessible(true);
							String rootValue = fieldType.get(compareRoot).toString();
							String toValue = fieldType.get(compareRootTo).toString();
							if (null == rootValue || null == toValue || !rootValue.equals(toValue)) {
								valuesOk = false;
							}
						}
					} catch (NoSuchFieldException | SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

					if (!setToReturn.contains(compareRoot) && !setToReturn.contains(compareRootTo) && valuesOk) {
						setToReturn.add(compareRoot);
					}
				}
			}
		}
		return setToReturn;
	}
}

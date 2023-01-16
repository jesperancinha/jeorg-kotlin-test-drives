package org.jesperancinha.string.paradigm.original.expression;

import java.util.ArrayList;
import java.util.List;

public class RegionSpellingCheck extends SpellingCheck {

	private final List<RegionImpl> spellingDoubleCommaRegions = new ArrayList<RegionImpl>();
	private final List<RegionImpl> spellingWhiteSpacesRegions = new ArrayList<RegionImpl>();
	private final List<RegionImpl> spellingBeginCapitalRegions = new ArrayList<RegionImpl>();

	public void checkSpelling(RegionImpl component) {
		if (checkDoubleCommas(component.getDescription())) {
			spellingDoubleCommaRegions.add(component);
		}
		if (checkWhiteSpaces(component.getDescription())) {
			spellingWhiteSpacesRegions.add(component);
		}
		String[] elements = component.getDescription().split(",");
		for (int i = 0; i < elements.length; i++) {
			if (checkCapitalLetter(elements, i)) {
				spellingBeginCapitalRegions.add(component);
			}
		}
	}

	public List<RegionImpl> getSpellingDoubleCommaRegions() {
		return spellingDoubleCommaRegions;
	}

	public List<RegionImpl> getSpellingWhiteSpacesRegions() {
		return spellingWhiteSpacesRegions;
	}

	public List<RegionImpl> getSpellingBeginCapitalRegions() {
		return spellingBeginCapitalRegions;
	}
}

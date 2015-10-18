package com.steelzack.string.paradigm.original.expression;

import java.util.ArrayList;
import java.util.List;

public class SpellingCheckImpl extends SpellingCheck {

	private final List<String> spellingDoubleCommaRegions = new ArrayList<String>();
	private final List<String> spellingWhiteSpacesRegions = new ArrayList<String>();
	private final List<String> spellingBeginCapitalRegions = new ArrayList<String>();

	public void checkSpelling(String text) {
		if (checkDoubleCommas(text)) {
			spellingDoubleCommaRegions.add(text);
		}
		if (checkWhiteSpaces(text)) {
			spellingWhiteSpacesRegions.add(text);
		}
		String[] elements = text.split(",");
		for (int i = 0; i < elements.length; i++) {
			if (checkCapitalLetter(elements, i)) {
				spellingBeginCapitalRegions.add(text);
			}
		}
	}

	public List<String> getSpellingDoubleCommaRegions() {
		return spellingDoubleCommaRegions;
	}

	public List<String> getSpellingWhiteSpacesRegions() {
		return spellingWhiteSpacesRegions;
	}

	public List<String> getSpellingBeginCapitalRegions() {
		return spellingBeginCapitalRegions;
	}
}

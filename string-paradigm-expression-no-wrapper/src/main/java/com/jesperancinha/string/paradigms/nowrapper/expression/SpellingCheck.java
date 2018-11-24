package com.jesperancinha.string.paradigms.nowrapper.expression;

import java.util.List;

public class SpellingCheck extends SpellingCheckBase {


	@SuppressWarnings("unchecked")
	public List<String> getSpellingDoubleCommaRegions() {
		return (List<String>)(List<?>)spellingDoubleCommaRegions;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSpellingWhiteSpacesRegions() {
		return (List<String>)(List<?>)spellingWhiteSpacesRegions;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSpellingBeginCapitalRegions() {
		return (List<String>)(List<?>)spellingBeginCapitalRegions;
	}
}

package com.steelzack.string.paradigms.nowrapper.expression;

import java.util.List;

public class RegionSpellingCheck extends SpellingCheckBase{



	@SuppressWarnings("unchecked")
	public List<RegionImpl> getSpellingDoubleCommaRegions() {
		return (List<RegionImpl>)(List<?>)spellingDoubleCommaRegions;
	}

	@SuppressWarnings("unchecked")
	public List<RegionImpl> getSpellingWhiteSpacesRegions() {
		return (List<RegionImpl>)(List<?>)spellingWhiteSpacesRegions;
	}

	@SuppressWarnings("unchecked")
	public List<RegionImpl> getSpellingBeginCapitalRegions() {
		return (List<RegionImpl>)(List<?>)spellingBeginCapitalRegions;
	}
}

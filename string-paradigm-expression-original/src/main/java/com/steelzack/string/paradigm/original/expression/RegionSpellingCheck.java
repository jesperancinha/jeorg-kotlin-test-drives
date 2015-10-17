package com.steelzack.string.paradigm.original.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegionSpellingCheck {

    private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
    private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

    private final List<RegionImpl> spellingDoubleCommaRegions = new ArrayList<RegionImpl>();
	private final List<RegionImpl> spellingWhiteSpacesRegions = new ArrayList<RegionImpl>();
	private final List<RegionImpl> spellingBeginCapitalRegions = new ArrayList<RegionImpl>();

    public void checkSpelling(RegionImpl component) {
    	if (DOUBLE_COMMAS.matcher(component.getDescription()).matches()) {
    	    spellingDoubleCommaRegions.add(component);
    	}
    	if (WHITE_SPACES.matcher(component.getDescription()).matches()) {
    	    spellingWhiteSpacesRegions.add(component);
    	}
    	String[] elements = component.getDescription().split(",");
    	for (int i = 0; i < elements.length; i++) {
    		String element = elements[i].trim();
    		if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.LOWERCASE_LETTER) {
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

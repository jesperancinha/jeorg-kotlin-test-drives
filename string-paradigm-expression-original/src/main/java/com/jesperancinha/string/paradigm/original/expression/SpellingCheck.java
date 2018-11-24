package com.jesperancinha.string.paradigm.original.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpellingCheck {

    private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
    private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

    private final List<String> spellingDoubleCommaRegions = new ArrayList<String>();
	private final List<String> spellingWhiteSpacesRegions = new ArrayList<String>();
	private final List<String> spellingBeginCapitalRegions = new ArrayList<String>();

    public void checkSpelling(String text) {
    	if (DOUBLE_COMMAS.matcher(text).matches()) {
    	    spellingDoubleCommaRegions.add(text);
    	}
    	if (WHITE_SPACES.matcher(text).matches()) {
    	    spellingWhiteSpacesRegions.add(text);
    	}
    	String[] elements = text.split(",");
    	for (int i = 0; i < elements.length; i++) {
    		String element = elements[i].trim();
    		if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.LOWERCASE_LETTER) {
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

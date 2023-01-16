package org.jesperancinha.string.paradigms.nowrapper.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpellingCheckBase {

    protected final List<Object> spellingDoubleCommaRegions = new ArrayList<Object>();
    protected final List<Object> spellingWhiteSpacesRegions = new ArrayList<Object>();
    protected final List<Object> spellingBeginCapitalRegions = new ArrayList<Object>();
    
    private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
    private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

    protected void checkSpelling(Object component) {
    	if (DOUBLE_COMMAS.matcher(component.toString()).matches()) {
    	    spellingDoubleCommaRegions.add(component);
    	}
    	if (WHITE_SPACES.matcher(component.toString()).matches()) {
    	    spellingWhiteSpacesRegions.add(component);
    	}
    	String[] elements = component.toString().split(",");
    	for (int i = 0; i < elements.length; i++) {
    		String element = elements[i].trim();
    		if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.LOWERCASE_LETTER) {
    		    spellingBeginCapitalRegions.add(component);
    		}
    	}
    }
}

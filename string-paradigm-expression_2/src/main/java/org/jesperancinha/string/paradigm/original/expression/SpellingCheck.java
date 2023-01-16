package org.jesperancinha.string.paradigm.original.expression;

import java.util.regex.Pattern;

public abstract class SpellingCheck {
	private static final Pattern DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*");
	private static final Pattern WHITE_SPACES = Pattern.compile(".*\\s\\s+.*");

	protected boolean checkDoubleCommas(String element) {
		return DOUBLE_COMMAS.matcher(element).matches();
	}

	protected boolean checkWhiteSpaces(String text) {
		return WHITE_SPACES.matcher(text).matches();
	}

	protected boolean checkCapitalLetter(String[] elements, int i) {
		String element = elements[i].trim();
		// The original code was actually wrong. If the spelling checkes for
		// capital letter components then they must have capital letter
		if (element.length() > 0 && Character.getType(element.charAt(0)) == Character.UPPERCASE_LETTER) {
			return true;
		}
		return false;
	}
}

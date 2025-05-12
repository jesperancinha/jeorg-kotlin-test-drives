package org.jesperancinha.string.paradigm.original.expression

import java.util.regex.Pattern

abstract class SpellingCheck {
    protected fun checkDoubleCommas(element: String?): Boolean {
        return DOUBLE_COMMAS.matcher(element).matches()
    }

    protected fun checkWhiteSpaces(text: String?): Boolean {
        return WHITE_SPACES.matcher(text).matches()
    }

    protected fun checkCapitalLetter(elements: Array<String>, i: Int): Boolean {
        val element = elements[i].trim { it <= ' ' }
        // The original code was actually wrong. If the spelling checkes for
        // capital letter components then they must have capital letter
        return if (element.length > 0 && Character.getType(element[0]) == Character.UPPERCASE_LETTER.toInt()) {
            true
        } else false
    }

    companion object {
        private val DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*")
        private val WHITE_SPACES = Pattern.compile(".*\\s\\s+.*")
    }
}
package org.jesperancinha.string.paradigm.original.expression

import java.util.regex.Pattern

class SpellingCheck {
    val spellingDoubleCommaRegions: MutableList<String> = ArrayList()
    val spellingWhiteSpacesRegions: MutableList<String> = ArrayList()
    val spellingBeginCapitalRegions: MutableList<String> = ArrayList()
    fun checkSpelling(text: String) {
        if (DOUBLE_COMMAS.matcher(text).matches()) {
            spellingDoubleCommaRegions.add(text)
        }
        if (WHITE_SPACES.matcher(text).matches()) {
            spellingWhiteSpacesRegions.add(text)
        }
        val elements = text.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in elements.indices) {
            val element = elements[i].trim { it <= ' ' }
            if (element.length > 0 && Character.getType(element[0]) == Character.LOWERCASE_LETTER.toInt()) {
                spellingBeginCapitalRegions.add(text)
            }
        }
    }

    companion object {
        private val DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*")
        private val WHITE_SPACES = Pattern.compile(".*\\s\\s+.*")
    }
}
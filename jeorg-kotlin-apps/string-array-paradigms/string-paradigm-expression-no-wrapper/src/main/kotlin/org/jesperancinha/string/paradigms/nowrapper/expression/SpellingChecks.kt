package org.jesperancinha.string.paradigms.nowrapper.expression

import java.util.regex.Pattern

abstract class SpellingCheckBase<T> {
    open val spellingDoubleCommaRegions: MutableList<T> = ArrayList()
    open val spellingWhiteSpacesRegions: MutableList<T> = ArrayList()
    open val spellingBeginCapitalRegions: MutableList<T> = ArrayList()
    fun checkSpelling(component: T) {
        if (DOUBLE_COMMAS.matcher(component.toString()).matches()) {
            spellingDoubleCommaRegions.add(component)
        }
        if (WHITE_SPACES.matcher(component.toString()).matches()) {
            spellingWhiteSpacesRegions.add(component)
        }
        val elements = component.toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in elements.indices) {
            val element = elements[i].trim { it <= ' ' }
            if (element.isNotEmpty() && Character.getType(element[0]) == Character.LOWERCASE_LETTER.toInt()) {
                spellingBeginCapitalRegions.add(component)
            }
        }
    }

    companion object {
        private val DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*")
        private val WHITE_SPACES = Pattern.compile(".*\\s\\s+.*")
    }
}

class SpellingCheck : SpellingCheckBase<String>()
class RegionSpellingCheck<T> : SpellingCheckBase<T>()
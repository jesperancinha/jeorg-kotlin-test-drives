package org.jesperancinha.string.paradigm.original.expression

import java.util.regex.Pattern

class RegionSpellingCheck {
    val spellingDoubleCommaRegions: MutableList<RegionImpl> = ArrayList()
    val spellingWhiteSpacesRegions: MutableList<RegionImpl> = ArrayList()
    val spellingBeginCapitalRegions: MutableList<RegionImpl> = ArrayList()
    fun checkSpelling(component: RegionImpl) {
        if (DOUBLE_COMMAS.matcher(component.description).matches()) {
            spellingDoubleCommaRegions.add(component)
        }
        if (WHITE_SPACES.matcher(component.description).matches()) {
            spellingWhiteSpacesRegions.add(component)
        }
        val elements = component.description.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in elements.indices) {
            val element = elements[i].trim { it <= ' ' }
            if (element.length > 0 && Character.getType(element[0]) == Character.LOWERCASE_LETTER.toInt()) {
                spellingBeginCapitalRegions.add(component)
            }
        }
    }

    companion object {
        private val DOUBLE_COMMAS = Pattern.compile(".*,\\s*,.*")
        private val WHITE_SPACES = Pattern.compile(".*\\s\\s+.*")
    }
}
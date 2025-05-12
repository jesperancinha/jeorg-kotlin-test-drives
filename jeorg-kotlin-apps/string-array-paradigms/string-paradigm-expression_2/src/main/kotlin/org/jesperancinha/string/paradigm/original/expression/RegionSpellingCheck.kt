package org.jesperancinha.string.paradigm.original.expression

class RegionSpellingCheck : SpellingCheck() {
    val spellingDoubleCommaRegions: MutableList<RegionImpl> = ArrayList()
    val spellingWhiteSpacesRegions: MutableList<RegionImpl> = ArrayList()
    val spellingBeginCapitalRegions: MutableList<RegionImpl> = ArrayList()
    fun checkSpelling(component: RegionImpl) {
        if (checkDoubleCommas(component.description)) {
            spellingDoubleCommaRegions.add(component)
        }
        if (checkWhiteSpaces(component.description)) {
            spellingWhiteSpacesRegions.add(component)
        }
        val elements: Array<String> = component.description.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        for (i in elements.indices) {
            if (checkCapitalLetter(elements, i)) {
                spellingBeginCapitalRegions.add(component)
            }
        }
    }
}
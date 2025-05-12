package org.jesperancinha.string.paradigm.original.expression

class SpellingCheckImpl : SpellingCheck() {
    val spellingDoubleCommaRegions: MutableList<String> = ArrayList()
    val spellingWhiteSpacesRegions: MutableList<String> = ArrayList()
    val spellingBeginCapitalRegions: MutableList<String> = ArrayList()
    fun checkSpelling(text: String) {
        if (checkDoubleCommas(text)) {
            spellingDoubleCommaRegions.add(text)
        }
        if (checkWhiteSpaces(text)) {
            spellingWhiteSpacesRegions.add(text)
        }
        val elements: Array<String> = text.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in elements.indices) {
            if (checkCapitalLetter(elements, i)) {
                spellingBeginCapitalRegions.add(text)
            }
        }
    }
}
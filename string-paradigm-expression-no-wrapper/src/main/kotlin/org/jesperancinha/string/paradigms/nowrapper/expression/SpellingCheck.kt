package org.jesperancinha.string.paradigms.nowrapper.expression

class SpellingCheck : SpellingCheckBase() {
    override val spellingDoubleCommaRegions: List<String>?
        get() = spellingDoubleCommaRegions as List<*> as List<String>
    override val spellingWhiteSpacesRegions: List<String>?
        get() = spellingWhiteSpacesRegions as List<*> as List<String>
    override val spellingBeginCapitalRegions: List<String>?
        get() = spellingBeginCapitalRegions as List<*> as List<String>
}
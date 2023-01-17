package org.jesperancinha.string.paradigms.nowrapper.expression

class RegionSpellingCheck : SpellingCheckBase() {
    override val spellingDoubleCommaRegions: List<RegionImpl>?
        get() = spellingDoubleCommaRegions as List<*> as List<RegionImpl>
    override val spellingWhiteSpacesRegions: List<RegionImpl>?
        get() = spellingWhiteSpacesRegions as List<*> as List<RegionImpl>
    override val spellingBeginCapitalRegions: List<RegionImpl>?
        get() = spellingBeginCapitalRegions as List<*> as List<RegionImpl>
}
package org.jesperancinha.string.paradigm.original.expression

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldStartWith
import org.junit.jupiter.api.Test

class RegionSpellingCheckTest {
    @Test
    fun testCheckSpelling() {
        val regions = arrayOf(ALGARVE, MINHO, RIBATEJO)
        val assignedNumbers = arrayOf("1'", "2'", ",3")
        val components: MutableList<RegionImpl> = ArrayList()
        for (type in regions) {
            for (size in assignedNumbers) {
                components.add(RegionImpl(type, size))
            }
        }
        val check = RegionSpellingCheck()
        for (component in components) {
            check.checkSpelling(component)
        }
        val resultSpellingBeginCapitalRegions: List<RegionImpl> = check.spellingBeginCapitalRegions
        resultSpellingBeginCapitalRegions.shouldHaveSize(6)
        resultSpellingBeginCapitalRegions[0].regionName.shouldStartWith(ALGARVE)
        resultSpellingBeginCapitalRegions[1].regionName.shouldStartWith(ALGARVE)
        resultSpellingBeginCapitalRegions[2].regionName.shouldStartWith(ALGARVE)
        resultSpellingBeginCapitalRegions[3].regionName.shouldStartWith(RIBATEJO)
        resultSpellingBeginCapitalRegions[4].regionName.shouldStartWith(RIBATEJO)
        resultSpellingBeginCapitalRegions[5].regionName.shouldStartWith(RIBATEJO)
        val resultSpellingDoubleCommaRegions: List<RegionImpl> = check.spellingDoubleCommaRegions
        resultSpellingDoubleCommaRegions.shouldHaveSize(3)
        for (region in resultSpellingDoubleCommaRegions) {
            region.assignedNumber.shouldStartWith(",3")
        }
        val resultSpellingWhiteSpacesRegions: List<RegionImpl> = check.spellingWhiteSpacesRegions
        resultSpellingWhiteSpacesRegions.shouldHaveSize(3)
        for (region in resultSpellingWhiteSpacesRegions) {
            region.regionName.shouldStartWith(RIBATEJO)
        }
    }

    companion object {
        private const val ALGARVE = "Algarve"
        private const val RIBATEJO = "  Ribatejo"
        private const val MINHO = "minho"
    }
}
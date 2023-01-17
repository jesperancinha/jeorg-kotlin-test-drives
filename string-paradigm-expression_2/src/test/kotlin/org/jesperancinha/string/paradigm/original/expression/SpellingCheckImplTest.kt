package org.jesperancinha.string.paradigm.original.expression

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldStartWith
import org.junit.jupiter.api.Test

class SpellingCheckImplTest {
    @Test
    fun testCheckSpelling() {
        val regions = arrayOf(ALGARVE, MINHO, RIBATEJO, EXTREMADURA)
        val assignedNumbers = arrayOf(_1, _2, _3)
        val check = SpellingCheckImpl()
        for (region in regions) {
            check.checkSpelling(region)
        }
        for (number in assignedNumbers) {
            check.checkSpelling(number)
        }
        val resultSpellingBeginCapitalRegions = check.spellingBeginCapitalRegions
        resultSpellingBeginCapitalRegions[0].shouldStartWith(ALGARVE)
        resultSpellingBeginCapitalRegions[1].shouldStartWith(RIBATEJO)
        val resultSpellingDoubleCommaRegions = check.spellingDoubleCommaRegions
        resultSpellingDoubleCommaRegions.shouldHaveSize(1)
        for (stringResult in resultSpellingDoubleCommaRegions) {
            stringResult.shouldStartWith(EXTREMADURA)
        }
        val resultSpellingWhiteSpacesRegions = check.spellingWhiteSpacesRegions
        resultSpellingWhiteSpacesRegions.shouldHaveSize(1)
        for (stringResult in resultSpellingWhiteSpacesRegions) {
            stringResult.shouldStartWith(RIBATEJO)
        }
    }

    companion object {
        private const val _1 = "1'"
        private const val _2 = "2'"
        private const val _3 = ",3"
        private const val ALGARVE = "Algarve"
        private const val EXTREMADURA = "Tras Os Montes E Alto Douro, , extremadura"
        private const val RIBATEJO = "  Ribatejo"
        private const val MINHO = "minho"
    }
}
package com.jesperancinha.test.objects.bufferedreader

import com.google.common.truth.Truth
import com.jesperancinha.test.objects.bufferedreader.NanoStabilityChecker
import com.jesperancinha.test.objects.bufferedreader.NanoStabilityChecker.Companion.getStats
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 * @author JOAO
 */
class NanoStabilityCheckerTest {
    @Test
    fun testPrintElementTo_100000_elements() {
        val checker = NanoStabilityChecker()
        val testStringFirst = "Test string-" + Math.random()
        val stats = getStats(NUMBER_OF_TEST_CALLS, testStringFirst, checker)
        val testString = "Test string" + Math.random()
        val resultTimeForThisCheck = checker.printElementTo(testString, System.out)
        logger.info("Time for check is {}", resultTimeForThisCheck)
        testInToleranceRange(resultTimeForThisCheck, stats)
    }

    private fun testInToleranceRange(resultTimeForThisCheck: Long, stats: Stats) {
        val maxValue = stats.max
        val minValue = stats.min
        Truth.assertThat(resultTimeForThisCheck).isAtLeast(minValue)
        Truth.assertThat(resultTimeForThisCheck).isAtMost(maxValue)
        logger.info(String.format("Test passed for check between %d and %d", minValue, maxValue))
    }

    companion object {
        private const val NUMBER_OF_TEST_CALLS = 100000
        private val logger = LoggerFactory.getLogger(NanoStabilityChecker::class.java)
    }
}
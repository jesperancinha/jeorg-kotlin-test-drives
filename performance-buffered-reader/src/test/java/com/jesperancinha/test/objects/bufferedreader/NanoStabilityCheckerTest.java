package com.jesperancinha.test.objects.bufferedreader;

import static com.google.common.truth.Truth.assertThat;
import static com.jesperancinha.test.objects.bufferedreader.NanoStabilityChecker.getStats;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JOAO
 */
public class NanoStabilityCheckerTest {
	private static final int NUMBER_OF_TEST_CALLS = 1000000;

	private static final Logger logger = LoggerFactory.getLogger(NanoStabilityChecker.class);

	@Test
	public void testPrintElementTo_100000_elements() {
		final NanoStabilityChecker checker = new NanoStabilityChecker();
		final String testStringFirst = "Test string-" + Math.random();

		final Stats stats = getStats(NUMBER_OF_TEST_CALLS, testStringFirst, checker);

		for (int i = 1; i <= NUMBER_OF_TEST_CALLS; i++) {
			final String testString = "Test string" + Math.random();
			final long resultTimeForThisCheck = checker.printElementTo(testString, System.out);
			logger.info("Time for check {} is {}", i, resultTimeForThisCheck);
			testInToleranceRange(resultTimeForThisCheck, stats);
		}
	}

	private void testInToleranceRange(long resultTimeForThisCheck, Stats stats) {
		long maxValue = stats.getMax();
		long minValue = stats.getMin();
		assertThat(resultTimeForThisCheck).isAtLeast(minValue);
		assertThat(resultTimeForThisCheck).isAtMost(maxValue);
		logger.info(String.format("Test passed for check between %d and %d", minValue, maxValue));
	}

}

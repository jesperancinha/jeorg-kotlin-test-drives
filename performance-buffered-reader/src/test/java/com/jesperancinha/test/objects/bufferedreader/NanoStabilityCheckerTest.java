package com.jesperancinha.test.objects.bufferedreader;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Test;

/**
 * 
 * @author JOAO
 *
 */
public class NanoStabilityCheckerTest {
	private static final int NUMBER_OF_TEST_CALLS = 100000;

	private static final Logger logger = Logger.getLogger(NanoStabilityChecker.class.getName());

	@Test
	public void testPrintElementTo_100000_elements() {
		final NanoStabilityChecker checker = new NanoStabilityChecker();
		final String testStringFirst = "Test string-" + Math.random();

		final Stats stats = getStats(NUMBER_OF_TEST_CALLS, testStringFirst, checker);

		for (int i = 1; i <= NUMBER_OF_TEST_CALLS; i++) {
			final String testString = "Test string" + Math.random();
			final long resultTimeForThisCheck = checker.printElementTo(testString, System.out);
			logger.info(String.format("Time for check %d is %d", i, resultTimeForThisCheck));
			testInToleranceRange(resultTimeForThisCheck, stats);
		}
	}

	private void testInToleranceRange(long resultTimeForThisCheck, Stats stats) {
		long maxValue = stats.getMax();
		long minValue = stats.getMin();
		assertTrue(resultTimeForThisCheck >= minValue ? resultTimeForThisCheck <= maxValue : false);
		logger.info(String.format("Test passed for check between %d and %d", minValue, maxValue));
	}

	private Stats getStats(int numberOfCalls, String testString, NanoStabilityChecker checker) {
		long average = checker.printElementTo(testString, System.out);
		long max = average;
		long min = average;
		for (int i = 0; i < numberOfCalls; i++) {
			final long printElementToTime = checker.printElementTo(testString, System.out);
			if (printElementToTime > max) {
				max = printElementToTime;
			}

			if (printElementToTime < min) {
				min = printElementToTime;
			}
			average = (average + printElementToTime) / 2;
		}
		final Stats stats = new Stats();
		logger.info(String.format("Time for average check is %d ", average));
		logger.info(String.format("Time for minimum check is %d ", min));
		logger.info(String.format("Time for maximum check is %d ", max));
		stats.setAverage(average);
		stats.setMax(max);
		stats.setMin(min);
		return stats;
	}
}

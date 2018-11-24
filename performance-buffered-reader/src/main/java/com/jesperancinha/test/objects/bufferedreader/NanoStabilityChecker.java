package com.jesperancinha.test.objects.bufferedreader;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used for testing the reliability of using nanoseconds in performance for unit
 * The idea is that more calls will reduce the possibility of the stats to be wrong
 * cases.
 *
 * @author JOAO
 */
class NanoStabilityChecker {

	private static Logger logger = LoggerFactory.getLogger(NanoStabilityChecker.class);

	long printElementTo(String sentence, PrintStream out) {
		long startMarker = System.nanoTime();
		out.println(sentence);
		return System.nanoTime() - startMarker;
	}

	/**
	 * @param numberOfCalls
	 * @param testString
	 * @param checker
	 * @return
	 */
	static Stats getStats(int numberOfCalls, String testString, NanoStabilityChecker checker) {
		List<Integer> results = IntStream.range(0, numberOfCalls)
				.map(index -> (int) checker.printElementTo(testString, System.out)).boxed().collect(
						Collectors.toList());
		long max = results.stream().max(Integer::compareTo).orElse(0);
		long min = results.stream().min(Integer::compareTo).orElse(0);
		long total = results.stream().reduce(Integer::sum).orElse(0);
		final Stats stats = new Stats();
		long average = total / numberOfCalls;
		logger.info(String.format("Time for average check is %d ", average));
		logger.info(String.format("Time for minimum check is %d ", min));
		logger.info(String.format("Time for maximum check is %d ", max));
		stats.setAverage(average);
		stats.setMax(max);
		stats.setMin(min);
		return stats;
	}
}

package com.jesperancinha.performance.test.jumpsearch.benchmarking;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.Test;

import com.jesperancinha.performance.test.jumpsearch.JumpSearchFileSameStepMethod0;

/*
 * This jump algorithm as described in:
 * http://www.stoimen.com/blog/2011/12/12/computer-algorithms-jump-search/
 * Tries to calculate first generic step jump in order to search
 * Unlike the binary search model, it assumes that is better to jump a step and then perform the already known binary search
 * As for this implementation it is being created without consideration on performance using an IJW (It just works) model
 */
public class JumpSearchFileSameStepMethod0BenchmarkingTest {
	private static final Logger logger = Logger.getLogger(JumpSearchFileSameStepMethod0BenchmarkingTest.class.getName());

	/**
	 * This test provides the first benchmarking of this module It puts all the
	 * contents of a testFile in memory and then performs a search. After the
	 * full content is loaded in memory, measurements begin to see how long it
	 * takes to perform a long find.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBenchmarking00_10000() throws Exception {
		testBenchmarking00_helper("sample10000.txt", 20153, 9994);
	}

	@Test
	public void testBenchmarking00_100000() throws Exception {
		testBenchmarking00_helper("sample100000.txt", 200111, 99998);
	}

	@Test
	public void testBenchmarking00_1000000() throws Exception {
		testBenchmarking00_helper("sample1000000.txt", 1999358, 999998);
	}

	private void testBenchmarking00_helper(String sampleFile, int value, int expectedIndex)
			throws URISyntaxException, IOException {
		final File f = new File(getClass().getResource(sampleFile).toURI());
		String fullText;
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			fullText = br.readLine();
		}

		final int[] completeList = Arrays.asList(fullText.split(", ")).stream().mapToInt(Integer::parseInt).toArray();

		final Date timeStart = new Date();
		final int result = new JumpSearchFileSameStepMethod0().getNumberIndexFromArray(value, completeList);
		final Date timeEnd = new Date();
		assertEquals(value, completeList[result]);
		assertEquals(expectedIndex, result);

		final long miliseconds = timeEnd.getTime() - timeStart.getTime();
		logger.info(String.format(
				"Search completed in %d miliseconds for file %s with %d elements. Index found is %d for item %d",
				miliseconds, sampleFile, completeList.length, result, value));
	}
}

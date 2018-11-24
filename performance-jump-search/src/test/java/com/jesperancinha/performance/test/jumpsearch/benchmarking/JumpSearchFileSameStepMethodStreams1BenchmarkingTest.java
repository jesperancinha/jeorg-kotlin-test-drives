package com.jesperancinha.performance.test.jumpsearch.benchmarking;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;

import com.performance.test.jumpsearch.JumpSearchFileSameStepMethodStreams1;

public class JumpSearchFileSameStepMethodStreams1BenchmarkingTest {
	Logger LOG = Logger.getLogger(JumpSearchFileSameStepMethodStreams1BenchmarkingTest.class.getName());

	@Test
	public void testBenchmarking01_10000() throws Exception {
		testBenchmarking01_helper("sample10000.txt", 20153, 9994);
	}

	@Ignore(value = "This test takes too long. It's only here to show that performance can be lost by using less memory")
	@Test
	public void testBenchmarking01_100000() throws Exception {
		testBenchmarking01_helper("sample100000.txt", 200111, 99998);
	}

	@Ignore(value = "This test takes too long. It's only here to show that performance can be lost by using less memory")
	@Test
	public void testBenchmarking01_1000000() throws Exception {
		testBenchmarking01_helper("sample1000000.txt", 1999358, 999998);
	}

	private void testBenchmarking01_helper(String sampleFile, int value, int expectedIndex)
			throws URISyntaxException, IOException, FileNotFoundException {
		final InputStream inputStream = getClass().getResourceAsStream(sampleFile);

		final Date timeStart = new Date();
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(value, inputStream);
		final Date timeEnd = new Date();
		assertEquals(expectedIndex, result);

		final long miliseconds = timeEnd.getTime() - timeStart.getTime();
		LOG.info(String.format("Search completed in %d miliseconds for file %s. Index found is %d for item %d",
				miliseconds, sampleFile, result, value));
	}
}

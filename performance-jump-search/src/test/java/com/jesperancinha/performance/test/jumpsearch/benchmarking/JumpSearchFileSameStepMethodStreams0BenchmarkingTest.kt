package com.jesperancinha.performance.test.jumpsearch.benchmarking;

import com.jesperancinha.performance.test.jumpsearch.JumpSearchFileSameStepMethodStreams0;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JumpSearchFileSameStepMethodStreams0BenchmarkingTest {
    Logger LOG = Logger.getLogger(JumpSearchFileSameStepMethodStreams0BenchmarkingTest.class.getName());

    @Test
    public void testBenchmarking00_10000() {
        testBenchmarking00_helper("sample10000.txt", 20153, 9994);
    }

    @Disabled(value = "This test takes too long. It's only here to show that performance can be lost by using less memory")
    @Test
    public void testBenchmarking00_100000() {
        testBenchmarking00_helper("sample100000.txt", 200111, 99998);
    }

    @Disabled(value = "This test takes too long. It's only here to show that performance can be lost by using less memory")
    @Test
    public void testBenchmarking00_1000000() {
        testBenchmarking00_helper("sample1000000.txt", 1999358, 999998);
    }

    private void testBenchmarking00_helper(String sampleFile, int value, int expectedIndex) {
        final InputStream inputStream = getClass().getResourceAsStream(sampleFile);

        final Date timeStart = new Date();
        final int result = new JumpSearchFileSameStepMethodStreams0().getNumberIndexFromArray(value, inputStream);
        final Date timeEnd = new Date();
        assertEquals(expectedIndex, result);

        final long miliseconds = timeEnd.getTime() - timeStart.getTime();
        LOG.info(String.format("Search completed in %d miliseconds for file %s. Index found is %d for item %d",
                miliseconds, sampleFile, result, value));
    }
}

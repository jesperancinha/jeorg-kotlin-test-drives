package com.jesperancinha.performance.test.jumpsearch.benchmarking

import com.jesperancinha.performance.test.jumpsearch.JumpSearchFileSameStepMethodStreams0
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.util.*
import java.util.logging.Logger

class JumpSearchFileSameStepMethodStreams0BenchmarkingTest {
    var LOG = Logger.getLogger(
        JumpSearchFileSameStepMethodStreams0BenchmarkingTest::class.java.name
    )

    @Test
    fun testBenchmarking00_10000() {
        testBenchmarking00_helper("sample10000.txt", 20153, 9994)
    }

    @Disabled(value = "This test takes too long. It's only here to show that performance can be lost by using less memory")
    @Test
    fun testBenchmarking00_100000() {
        testBenchmarking00_helper("sample100000.txt", 200111, 99998)
    }

    @Disabled(value = "This test takes too long. It's only here to show that performance can be lost by using less memory")
    @Test
    fun testBenchmarking00_1000000() {
        testBenchmarking00_helper("sample1000000.txt", 1999358, 999998)
    }

    private fun testBenchmarking00_helper(sampleFile: String, value: Int, expectedIndex: Int) {
        val inputStream = javaClass.getResourceAsStream(sampleFile)
        val timeStart = Date()
        val result = JumpSearchFileSameStepMethodStreams0().getNumberIndexFromArray(value, inputStream.shouldNotBeNull())
        val timeEnd = Date()
        result shouldBe expectedIndex
        val milliseconds = timeEnd.time - timeStart.time
        LOG.info(
            String.format(
                "Search completed in %d miliseconds for file %s. Index found is %d for item %d",
                milliseconds, sampleFile, result, value
            )
        )
    }
}
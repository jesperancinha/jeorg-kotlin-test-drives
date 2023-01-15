package com.jesperancinha.performance.test.jumpsearch.benchmarking

import com.jesperancinha.performance.test.jumpsearch.JumpSearchFileSameStepMethod0
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.*
import java.net.URISyntaxException
import java.util.*
import java.util.logging.Logger

/*
 * This jump algorithm as described in:
 * http://www.stoimen.com/blog/2011/12/12/computer-algorithms-jump-search/
 * Tries to calculate first generic step jump in order to search
 * Unlike the binary search model, it assumes that is better to jump a step and then perform the already known binary search
 * As for this implementation it is being created without consideration on performance using an IJW (It just works) model
 */
class JumpSearchFileSameStepMethod0BenchmarkingTest {
    /**
     * This test provides the first benchmarking of this module It puts all the
     * contents of a testFile in memory and then performs a search. After the
     * full content is loaded in memory, measurements begin to see how long it
     * takes to perform a long find.
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun testBenchmarking00_10000() {
        testBenchmarking00_helper("sample10000.txt", 20153, 9994)
    }

    @Test
    @Throws(Exception::class)
    fun testBenchmarking00_100000() {
        testBenchmarking00_helper("sample100000.txt", 200111, 99998)
    }

    @Test
    @Throws(Exception::class)
    fun testBenchmarking00_1000000() {
        testBenchmarking00_helper("sample1000000.txt", 1999358, 999998)
    }

    @Throws(URISyntaxException::class, IOException::class)
    private fun testBenchmarking00_helper(sampleFile: String, value: Int, expectedIndex: Int) {
        val f = File(javaClass.getResource(sampleFile).toURI())
        var fullText: String
        BufferedReader(FileReader(f)).use { br -> fullText = br.readLine() }
        val completeList = Arrays.stream(fullText.split(", ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()).mapToInt { s: String -> s.toInt() }.toArray()
        val timeStart = Date()
        val result = JumpSearchFileSameStepMethod0().getNumberIndexFromArray(value, completeList)
        val timeEnd = Date()
        Assertions.assertEquals(value, completeList[result])
        Assertions.assertEquals(expectedIndex, result)
        val miliseconds = timeEnd.time - timeStart.time
        logger.info(
            String.format(
                "Search completed in %d miliseconds for file %s with %d elements. Index found is %d for item %d",
                miliseconds, sampleFile, completeList.size, result, value
            )
        )
    }

    companion object {
        private val logger = Logger.getLogger(
            JumpSearchFileSameStepMethod0BenchmarkingTest::class.java.name
        )
    }
}
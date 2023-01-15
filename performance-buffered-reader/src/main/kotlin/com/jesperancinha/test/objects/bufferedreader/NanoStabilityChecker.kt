package com.jesperancinha.test.objects.bufferedreader

import org.slf4j.LoggerFactory
import java.io.PrintStream
import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * Used for testing the reliability of using nanoseconds in performance for unit
 * The idea is that more calls will reduce the possibility of the stats to be wrong
 * cases.
 *
 * @author JOAO
 */
internal class NanoStabilityChecker {
    fun printElementTo(sentence: String?, out: PrintStream): Long {
        val startMarker = System.nanoTime()
        out.println(sentence)
        return System.nanoTime() - startMarker
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NanoStabilityChecker::class.java)

        /**
         * @param numberOfCalls
         * @param testString
         * @param checker
         * @return
         */
		@JvmStatic
		fun getStats(numberOfCalls: Int, testString: String?, checker: NanoStabilityChecker): Stats {
            val results = IntStream.range(0, numberOfCalls)
                .map { index: Int -> checker.printElementTo(testString, System.out).toInt() }.boxed().collect(
                    Collectors.toList()
                )
            val max = results.stream().max { obj: Int, anotherInteger: Int? ->
                obj.compareTo(
                    anotherInteger!!
                )
            }.orElse(0).toLong()
            val min = results.stream().min { obj: Int, anotherInteger: Int? ->
                obj.compareTo(
                    anotherInteger!!
                )
            }.orElse(0).toLong()
            val total = results.stream().reduce { a: Int, b: Int -> Integer.sum(a, b) }.orElse(0).toLong()
            val stats = Stats()
            val average = total / numberOfCalls
            logger.info(String.format("Time for average check is %d ", average))
            logger.info(String.format("Time for minimum check is %d ", min))
            logger.info(String.format("Time for maximum check is %d ", max))
            stats.average = average
            stats.max = max
            stats.min = min
            return stats
        }
    }
}
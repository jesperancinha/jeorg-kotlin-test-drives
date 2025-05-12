package org.jesperancinha.test.drive.books

import org.junit.jupiter.api.Test
import java.util.*

class XmlTestRunnerTest {
    @Test
    @Throws(Exception::class)
    fun testMain() {
        val testFile = "testFile" + Math.random()
        val testIteration = "" + random.nextInt()
        val args = arrayOf(
            "-n", 
            testIteration, 
            "-b", 
            testFile, 
            "--noop"
        )
        main(args)
    }

    @Test
    @Throws(Exception::class)
    fun testMain_aliases() {
        val testFile = "testFile" + Math.random()
        val testIteration = "" + random.nextInt()
        val args = arrayOf(
            "--number-of-iterations", 
            testIteration, 
            "--book-test-file", 
            testFile, 
            "--noop"
        )
        main(args)
    }

    companion object {
        private val random = Random()
    }
}
package org.jesperancinha.test.drive.books

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

class XmlTestRunnerWorkerTest {
    @Test
    @Throws(Exception::class)
    fun testXmlTestRunnerWorker() {
        val testFile = "testFile" + Math.random()
        val testIteration = "" + random.nextInt()
        val args = arrayOf( //
            "-n",  //
            testIteration,  //
            "-b",  //
            testFile,  //
            "--noop" //
        )
        val runner = XmlTestRunnerWorker(args)
        Assertions.assertEquals(testIteration, runner.getnIterations().toString())
        Assertions.assertEquals(testFile, runner.xmlFile)
    }

    @Test
    @Throws(Exception::class)
    fun testRunTests_10_Iter() {
        val runner: XmlTestRunnerWorker = object : XmlTestRunnerWorker() {
            @get:Throws(FileNotFoundException::class)
            override val inputStreamFromFile: InputStream
                get() = javaClass.getResourceAsStream("testXmlFile.xml")

            override fun getnIterations(): Int? {
                return 10
            }
        }
        runner.runTests()
    }

    @Test
    @Throws(Exception::class)
    fun testRunTests_10000_Iter() {
        val runner: XmlTestRunnerWorker = object : XmlTestRunnerWorker() {
            @get:Throws(FileNotFoundException::class)
            override val inputStreamFromFile: InputStream
                get() = javaClass.getResourceAsStream("testXmlFile.xml")

            override fun getnIterations(): Int? {
                return 10000
            }
        }
        runner.runTests()
    }

    companion object {
        private val random = Random()
    }
}
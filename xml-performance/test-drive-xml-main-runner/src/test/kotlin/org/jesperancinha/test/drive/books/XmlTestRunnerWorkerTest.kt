package org.jesperancinha.test.drive.books

import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import picocli.CommandLine
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import kotlin.io.path.toPath

class XmlTestRunnerWorkerTest {
    @Test
    @Throws(Exception::class)
    fun testXmlTestRunnerWorker() {
        val testFile = "testFile" + Math.random()
        val testIteration = "" + random.nextInt()
        val args = arrayOf(
            "-n",
            testIteration,
            "-b",
            testFile,
            "--noop"
        )
        val xmlTestRunner = XmlTestRunner()
        CommandLine(xmlTestRunner).execute(*args)
        Assertions.assertEquals(testIteration, xmlTestRunner.numberOfIterations.toString())
        Assertions.assertEquals(testFile, xmlTestRunner.bookTestFile)
    }

    @Test
    @Throws(Exception::class)
    fun testRunTests_10_Iter() {
        val runner: XmlTestRunner = object : XmlTestRunner() {
            override var bookTestFile: String? =
                javaClass.getResource("testXmlFile.xml").shouldNotBeNull().toURI().toPath().toString()
            override var numberOfIterations: Int? = 10
        }
        runner.runTests()
    }

    @Test
    @Throws(Exception::class)
    fun testRunTests_10000_Iter() {
        val runner: XmlTestRunner = object : XmlTestRunner() {
            override var bookTestFile: String? =
                javaClass.getResource("testXmlFile.xml").shouldNotBeNull().toURI().toPath().toString()
            override var numberOfIterations: Int? = 10000
        }
        runner.runTests()
    }

    companion object {
        private val random = Random()
    }
}
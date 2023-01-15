package org.jesperancinha.test.drive.books

import org.jesperancinha.test.drive.books.XmlBookManagerType.*
import org.jesperancinha.test.drive.xml.*
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.concurrent.Callable
import java.util.logging.Level
import java.util.logging.Logger

@Command(
    name = "XMl Test Runnen", mixinStandardHelpOptions = true, version = ["0.0.0"],
    description = ["Performs XML Parsing tests"]
)
open class XmlTestRunner : Callable<Int> {

    @Option(names = ["--book-test-file", "-b"], required = true)
   open var bookTestFile: String? = null

    @Option(names = ["--number-of-iterations", "-n"], required = true)
    open var numberOfIterations: Int? = null

    @Option(names = ["--noop"], required = false)
   open var isNoop = false

    @Throws(Exception::class)
    fun runTests(): Boolean {
        numberOfIterations?.let {
            callParseIterations(it, DOM)
            callParseIterations(it, DOM_XPATH)
            callParseIterations(it, SAX)
            callParseIterations(it, JAXB)
        } ?: return false
        return true
    }

    @Throws(Exception::class)
    private fun callParseIterations(nIterations: Int, type: XmlBookManagerType) {
        logger.log(Level.INFO, "Start of test for {0} with {1} iterations", arrayOf<Any?>(type, nIterations))
        val timeStampNow = System.nanoTime()
        for (i in 0 until nIterations) {
            try {
                val ioStream = FileInputStream(File(bookTestFile))
                val manager = getManagerFor(ioStream, type)
                manager.init()
            } catch (e: Exception) {
                throw RuntimeException(String.format("Test failed on iteration: %d", i), e)
            }
        }
        logger.log(
            Level.INFO,
            "End of test for {0} with {1} iterations. Took {2} ms",
            arrayOf<Any?>(type, nIterations, (System.nanoTime() - timeStampNow) / 1000000)
        )
    }

    private fun getManagerFor(ioStream: InputStream, type: XmlBookManagerType): XmlBookParserBuilder {
        return when (type) {
            DOM -> XmlBookDomParserManager(ioStream)
            DOM_XPATH -> XmlBookDomXpathParserManager(ioStream)
            JAXB -> XmlBookJAXBParserManager(ioStream)
            SAX -> XmlBookSAXParserManager(ioStream)
        }
    }

    override fun call(): Int = if (runTests()) 0 else 1

    companion object {
        private val logger = Logger.getLogger(XmlTestRunner::class.java.name)
    }
}
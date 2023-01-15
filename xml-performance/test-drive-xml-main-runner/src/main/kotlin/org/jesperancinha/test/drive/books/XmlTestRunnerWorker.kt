package org.jesperancinha.test.drive.books

import org.jesperancinha.test.drive.xml.*
import org.kohsuke.args4j.CmdLineParser
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.logging.Level
import java.util.logging.Logger

open class XmlTestRunnerWorker {
    private val logger = Logger.getLogger(XmlTestRunner::class.java.name)
    var xmlFile: String? = null
        private set
    private var nIterations: Int? = null
    var isNoop = false
        private set

    constructor()
    constructor(args: Array<String>) {
        val options = createNewOptions()
        val parser = CmdLineParser(options)
        parser.parseArgument(*args)
        xmlFile = options.bookTestFile
        nIterations = options.numberOfIterations
        isNoop = options.isNoop
    }

    fun createNewOptions(): XmlTestRunnerOptions {
        return XmlTestRunnerOptions()
    }

    @Throws(Exception::class)
    fun runTests(): Boolean {
        callParseIterations(getnIterations(), XmlBookManagerType.DOM)
        callParseIterations(getnIterations(), XmlBookManagerType.DOM_XPATH)
        callParseIterations(getnIterations(), XmlBookManagerType.SAX)
        callParseIterations(getnIterations(), XmlBookManagerType.JAXB)
        return true
    }

    @get:Throws(FileNotFoundException::class)
    open val inputStreamFromFile: InputStream
        get() = FileInputStream(File(xmlFile))

    @Throws(Exception::class)
    private fun callParseIterations(nIterations: Int?, type: XmlBookManagerType) {
        logger.log(Level.INFO, "Start of test for {0} with {1} iterations", arrayOf<Any?>(type, nIterations))
        val timeStampNow = System.nanoTime()
        for (i in 0 until nIterations!!) {
            try {
                val ioStream = inputStreamFromFile
                val manager = getManagerFor(ioStream, type)
                manager!!.init()
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

    private fun getManagerFor(ioStream: InputStream, type: XmlBookManagerType): XmlBookParserBuilder? {
        return when (type) {
            XmlBookManagerType.DOM -> XmlBookDomParserManager(ioStream)
            XmlBookManagerType.DOM_XPATH -> XmlBookDomXpathParserManager(ioStream)
            XmlBookManagerType.JAXB -> XmlBookJAXBParserManager(ioStream)
            XmlBookManagerType.SAX -> XmlBookSAXParserManager(ioStream)
        }
        return null
    }

    open fun getnIterations(): Int? {
        return nIterations
    }
}
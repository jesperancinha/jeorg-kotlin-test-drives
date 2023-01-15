package org.jesperancinha.test.drive.xml

import java.io.IOException
import java.io.InputStream
import java.util.concurrent.atomic.AtomicBoolean
import java.util.logging.Level
import java.util.logging.Logger

abstract class XmlBookParserBuilder(
    val ioStream: InputStream
) {

    val status by lazy { AtomicBoolean(true) }

    /**
     * Close has no effect. This is why we need state for this case
     */
    fun closeStream(): Unit =
        try {
            if(!status.get()){
                throw IOException()
            }
            ioStream.close()
            status.set(false)
        } catch (e: IOException) {
            logger.log(Level.WARNING, "Stream didn't close!", e)
            throw e
        }

    abstract fun getBookTitle(bookNumber: Int): String?
    abstract fun getNumberOfPages(bookNumber: Int): Int
    abstract fun getGenre(bookNumber: Int): String?
    abstract fun getAuthor(bookNumber: Int): String?

    abstract val numberOfBooks: Int

    @Throws(Exception::class)
    abstract fun init()

    companion object {
        private val logger = Logger.getLogger(XmlBookParserBuilder::class.java.name)
    }
}
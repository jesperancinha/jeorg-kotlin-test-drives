package org.jesperancinha.test.drive.xml

import org.jesperancinha.test.drive.sax.books.SAXBooksParser
import org.xml.sax.InputSource
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

class XmlBookSAXParserManager(ioStream: InputStream) : XmlBookParserBuilder(ioStream) {
    private val handler: SAXBooksParser by lazy { SAXBooksParser() }
    override fun getBookTitle(bookNumber: Int): String? {
        return handler.getBooks()[bookNumber].title
    }

    override fun getNumberOfPages(bookNumber: Int): Int {
        return handler.getBooks()[bookNumber].pages
    }

    override fun getGenre(bookNumber: Int): String? {
        return handler.getBooks()[bookNumber].genre
    }

    override fun getAuthor(bookNumber: Int): String? {
        return handler.getBooks()[bookNumber].author
    }

    override val numberOfBooks: Int by lazy { handler.getBooks().size }

    @Throws(Exception::class)
    override fun init() {
        val spf = SAXParserFactory.newInstance()
        spf.isNamespaceAware = true
        val saxParser = spf.newSAXParser()
        val xmlReader = saxParser.xmlReader
        xmlReader.contentHandler = handler
        xmlReader.parse(InputSource(ioStream))
    }
}
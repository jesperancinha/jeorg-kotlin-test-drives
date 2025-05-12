package org.jesperancinha.test.drive.sax.books

import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.util.*

class SAXBooksParser : DefaultHandler() {
    private var lastValue: String? = null
    private var currentBook: Book? = null
    private val books: MutableList<Book> = ArrayList()
    fun getBooks(): List<Book> {
        return books
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
        super.startElement(uri, localName, qName, attributes)
        if (localName === BOOK) {
            currentBook = Book()
            currentBook?.title = attributes.getValue(NAME)
        }
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, localName: String, qName: String) {
        super.endElement(uri, localName, qName)
        when (localName) {
            PAGES -> currentBook?.pages = lastValue?.toInt() ?: 0
            GENRE -> currentBook?.genre = lastValue
            AUTHOR -> currentBook?.author = lastValue
            BOOK -> currentBook?.let { books.add(it) }
        }
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        super.characters(ch, start, length)
        lastValue = String(ch.copyOfRange(start, start + length))
    }

    companion object {
        private const val AUTHOR = "author"
        private const val BOOK = "book"
        private const val GENRE = "genre"
        private const val PAGES = "pages"
        private const val NAME = "name"
    }
}
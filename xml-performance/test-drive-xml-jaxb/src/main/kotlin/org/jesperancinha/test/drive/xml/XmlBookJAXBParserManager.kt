package org.jesperancinha.test.drive.xml

import jakarta.xml.bind.JAXBContext
import org.jesperancinha.test.drive.jaxb.books.xml.Books
import java.io.InputStream

class XmlBookJAXBParserManager(ioStream: InputStream) : XmlBookParserBuilder(ioStream) {
    lateinit var books: Books
    override fun getBookTitle(bookNumber: Int): String? {
        return books.books[bookNumber].title
    }

    override fun getNumberOfPages(bookNumber: Int): Int {
        return books.books[bookNumber].pages
    }

    override fun getGenre(bookNumber: Int): String? {
        return books.books[bookNumber].genre
    }

    override fun getAuthor(bookNumber: Int): String? {
        return books.books[bookNumber].author
    }

    override val numberOfBooks: Int
        get() = books.books.size

    @Throws(Exception::class)
    override fun init() {
        val context = JAXBContext.newInstance(Books::class.java)
        val u = context.createUnmarshaller()
        books = u.unmarshal(ioStream) as Books
    }
}
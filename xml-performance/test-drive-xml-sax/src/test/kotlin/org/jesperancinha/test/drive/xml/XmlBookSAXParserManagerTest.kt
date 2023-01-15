package org.jesperancinha.test.drive.xml

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class XmlBookSAXParserManagerTest {
    @Test
    @Throws(Exception::class)
    fun testGetBookTitle() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm = XmlBookSAXParserManager(inputStream)
        pm.init()
        val bookTitle0 = pm.getBookTitle(0)
        val bookTitle1 = pm.getBookTitle(1)
        val bookTitle2 = pm.getBookTitle(2)
        val bookTitle3 = pm.getBookTitle(3)
        assertEquals("The Hobbit 0", bookTitle0)
        assertEquals("The Hobbit 1", bookTitle1)
        assertEquals("The Hobbit 2", bookTitle2)
        assertEquals("The Hobbit 3", bookTitle3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetNumberOfPages() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm = XmlBookSAXParserManager(inputStream)
        pm.init()
        val bookPages0 = pm.getNumberOfPages(0)
        val bookPages1 = pm.getNumberOfPages(1)
        val bookPages2 = pm.getNumberOfPages(2)
        val bookPages3 = pm.getNumberOfPages(3)
        assertEquals(123, bookPages0)
        assertEquals(456, bookPages1)
        assertEquals(789, bookPages2)
        assertEquals(101112, bookPages3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetGenre() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm: XmlBookParserBuilder = XmlBookSAXParserManager(inputStream)
        pm.init()
        val bookGenre0 = pm.getGenre(0)
        val bookGenre1 = pm.getGenre(1)
        val bookGenre2 = pm.getGenre(2)
        val bookGenre3 = pm.getGenre(3)
        assertEquals("Action", bookGenre0)
        assertEquals("Comedy", bookGenre1)
        assertEquals("War", bookGenre2)
        assertEquals("Drama", bookGenre3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetAuthor() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm = XmlBookSAXParserManager(inputStream)
        pm.init()
        val bookAuthor0 = pm.getAuthor(0)
        val bookAuthor1 = pm.getAuthor(1)
        val bookAuthor2 = pm.getAuthor(2)
        val bookAuthor3 = pm.getAuthor(3)
        assertEquals("J.R.R. Tolkien 1", bookAuthor0)
        assertEquals("J.R.R. Tolkien 2", bookAuthor1)
        assertEquals("J.R.R. Tolkien 3", bookAuthor2)
        assertEquals("J.R.R. Tolkien 4", bookAuthor3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetNumberOfBooks() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm = XmlBookSAXParserManager(inputStream)
        pm.init()
        val nPages = pm.numberOfBooks
        assertEquals(4, nPages)
    }
}
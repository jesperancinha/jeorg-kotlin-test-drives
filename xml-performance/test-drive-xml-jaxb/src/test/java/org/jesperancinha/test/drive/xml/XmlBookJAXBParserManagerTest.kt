package org.jesperancinha.test.drive.xml

import org.junit.Assert
import org.junit.Test

class XmlBookJAXBParserManagerTest {
    @Test
    @Throws(Exception::class)
    fun testGetBookTitle() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm: XmlBookParserBuilder = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookTitle0 = pm.getBookTitle(0)
        val bookTitle1 = pm.getBookTitle(1)
        val bookTitle2 = pm.getBookTitle(2)
        val bookTitle3 = pm.getBookTitle(3)
        Assert.assertEquals("The Hobbit 0", bookTitle0)
        Assert.assertEquals("The Hobbit 1", bookTitle1)
        Assert.assertEquals("The Hobbit 2", bookTitle2)
        Assert.assertEquals("The Hobbit 3", bookTitle3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetNumberOfPages() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm: XmlBookParserBuilder = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookPages0 = pm.getNumberOfPages(0)
        val bookPages1 = pm.getNumberOfPages(1)
        val bookPages2 = pm.getNumberOfPages(2)
        val bookPages3 = pm.getNumberOfPages(3)
        Assert.assertEquals(123, bookPages0.toLong())
        Assert.assertEquals(456, bookPages1.toLong())
        Assert.assertEquals(789, bookPages2.toLong())
        Assert.assertEquals(101112, bookPages3.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun testGetGenre() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm: XmlBookParserBuilder = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookGenre0 = pm.getGenre(0)
        val bookGenre1 = pm.getGenre(1)
        val bookGenre2 = pm.getGenre(2)
        val bookGenre3 = pm.getGenre(3)
        Assert.assertEquals("Action", bookGenre0)
        Assert.assertEquals("Comedy", bookGenre1)
        Assert.assertEquals("War", bookGenre2)
        Assert.assertEquals("Drama", bookGenre3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetAuthor() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm: XmlBookParserBuilder = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookAuthor0 = pm.getAuthor(0)
        val bookAuthor1 = pm.getAuthor(1)
        val bookAuthor2 = pm.getAuthor(2)
        val bookAuthor3 = pm.getAuthor(3)
        Assert.assertEquals("J.R.R. Tolkien 1", bookAuthor0)
        Assert.assertEquals("J.R.R. Tolkien 2", bookAuthor1)
        Assert.assertEquals("J.R.R. Tolkien 3", bookAuthor2)
        Assert.assertEquals("J.R.R. Tolkien 4", bookAuthor3)
    }

    @Test
    @Throws(Exception::class)
    fun testGetNumberOfBooks() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        val pm: XmlBookParserBuilder = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val nPages = pm.numberOfBooks
        Assert.assertEquals(4, nPages.toLong())
    }
}
package org.jesperancinha.test.drive.xml

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class XmlBookJAXBParserManagerTest {
    @Test
    @Throws(Exception::class)
    fun testGetBookTitle() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        inputStream.shouldNotBeNull()
        val pm = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookTitle0 = pm.getBookTitle(0)
        val bookTitle1 = pm.getBookTitle(1)
        val bookTitle2 = pm.getBookTitle(2)
        val bookTitle3 = pm.getBookTitle(3)
        bookTitle0 shouldBe "The Hobbit 0"
        bookTitle1 shouldBe "The Hobbit 1"
        bookTitle2 shouldBe "The Hobbit 2"
        bookTitle3 shouldBe "The Hobbit 3"
    }

    @Test
    @Throws(Exception::class)
    fun testGetNumberOfPages() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        inputStream.shouldNotBeNull()
        val pm = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookPages0 = pm.getNumberOfPages(0)
        val bookPages1 = pm.getNumberOfPages(1)
        val bookPages2 = pm.getNumberOfPages(2)
        val bookPages3 = pm.getNumberOfPages(3)
        bookPages0 shouldBe 123
        bookPages1 shouldBe 456
        bookPages2 shouldBe 789
        bookPages3 shouldBe 101112
    }

    @Test
    @Throws(Exception::class)
    fun testGetGenre() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        inputStream.shouldNotBeNull()
        val pm = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookGenre0 = pm.getGenre(0)
        val bookGenre1 = pm.getGenre(1)
        val bookGenre2 = pm.getGenre(2)
        val bookGenre3 = pm.getGenre(3)

        bookGenre0 shouldBe "Action"
        bookGenre1 shouldBe "Comedy"
        bookGenre2 shouldBe "War"
        bookGenre3 shouldBe "Drama"
    }

    @Test
    @Throws(Exception::class)
    fun testGetAuthor() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        inputStream.shouldNotBeNull()
        val pm = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val bookAuthor0 = pm.getAuthor(0)
        val bookAuthor1 = pm.getAuthor(1)
        val bookAuthor2 = pm.getAuthor(2)
        val bookAuthor3 = pm.getAuthor(3)

        bookAuthor0 shouldBe "J.R.R. Tolkien 1"
        bookAuthor1 shouldBe "J.R.R. Tolkien 2"
        bookAuthor2 shouldBe "J.R.R. Tolkien 3"
        bookAuthor3 shouldBe "J.R.R. Tolkien 4"
    }

    @Test
    @Throws(Exception::class)
    fun testGetNumberOfBooks() {
        val inputStream = javaClass.getResourceAsStream("testXmlFile.xml")
        inputStream.shouldNotBeNull()
        val pm = XmlBookJAXBParserManager(inputStream)
        pm.init()
        val nPages = pm.numberOfBooks
        nPages shouldBe 4
    }
}
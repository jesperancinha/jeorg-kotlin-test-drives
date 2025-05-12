package org.jesperancinha.test.drive.xml

import org.w3c.dom.Document
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStream
import javax.xml.namespace.NamespaceContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

class XmlBookDomXpathParserManager(ioStream: InputStream) : XmlBookParserBuilder(ioStream) {
    private val document by lazy {
        val dbf = DocumentBuilderFactory.newInstance()
        dbf.isNamespaceAware = true
        val db = dbf.newDocumentBuilder()
        db.parse(ioStream)
    }
    val xpath: XPath by lazy {
        XPathFactory.newInstance().newXPath()
            .apply {
                namespaceContext = object : NamespaceContext {
                    override fun getPrefixes(namespaceURI: String): Iterator<String>? {
                        return null
                    }

                    override fun getPrefix(namespaceURI: String): String {
                        return BS
                    }

                    override fun getNamespaceURI(prefix: String): String {
                        return HTTP_WWW_BOOKSHELFEXAMPLE_COM
                    }
                }
            }
    }
    private val bookList: NodeList by lazy {
        xpath.evaluate(BOOKS + BS + BOOK, document, XPathConstants.NODESET) as NodeList
    }

    override fun getBookTitle(bookNumber: Int): String? {
        return try {
            xpath!!.evaluate(NAME, bookList!!.item(bookNumber), XPathConstants.STRING) as String
        } catch (e: XPathExpressionException) {
            e.printStackTrace()
            null
        }
    }

    override fun getNumberOfPages(bookNumber: Int): Int {
        val stringPages = getValueFromNode(bookNumber, PAGES)
        return stringPages?.toInt() ?: -1
    }

    override fun getGenre(bookNumber: Int): String? {
        return getValueFromNode(bookNumber, GENRE)
    }

    override fun getAuthor(bookNumber: Int): String? {
        return getValueFromNode(bookNumber, AUTHOR)
    }

    override val numberOfBooks: Int
        get() = bookList.length

    override fun init() {
    }

    @Throws(
        SAXException::class,
        IOException::class,
        ParserConfigurationException::class,
        XPathExpressionException::class
    )
    private fun getValueFromNode(bookNumber: Int, nodeName: String): String? {
        return try {
            xpath.evaluate(BS + nodeName, bookList.item(bookNumber), XPathConstants.STRING) as String
        } catch (e: XPathExpressionException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val NAME = "@name"
        private const val BOOKS = "/books/"
        private const val BS = "bs:"
        private const val AUTHOR = "author"
        private const val GENRE = "genre"
        private const val PAGES = "pages"
        private const val BOOK = "book"
        private const val HTTP_WWW_BOOKSHELFEXAMPLE_COM = "http://www.bookshelfexample.com"
    }
}
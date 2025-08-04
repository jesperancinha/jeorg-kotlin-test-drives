package org.jesperancinha.test.drive.xml

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class XmlBookDomParserManager(ioStream: InputStream) : XmlBookParserBuilder(ioStream) {
    private var document: Document? = null
    private var bookList: NodeList? = null
    override fun getBookTitle(bookNumber: Int): String? {
        return bookList?.item(bookNumber)?.attributes?.getNamedItem("name")?.nodeValue
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
        get() = bookList?.length ?: 0

    @Throws(SAXException::class, IOException::class, ParserConfigurationException::class)
    override fun init() {
        val dbf = DocumentBuilderFactory.newInstance()
        dbf.isNamespaceAware = true
        val db = dbf.newDocumentBuilder()
        document = db.parse(ioStream)
        bookList = document?.getElementsByTagNameNS(HTTP_WWW_BOOKSHELFEXAMPLE_COM, BOOK)
    }

    private fun getValueFromNode(bookNumber: Int, nodeName: String): String? {
        val bookItem = bookList?.item(bookNumber) ?: return null
        var currentNode = bookItem.firstChild
        while (currentNode != null && 
            (currentNode.nodeType == Node.TEXT_NODE || currentNode.namespaceURI == HTTP_WWW_BOOKSHELFEXAMPLE_COM)
            && 
            (currentNode.localName == null || currentNode.localName != nodeName)
        ) {
            currentNode = currentNode.nextSibling
        }
        return currentNode?.textContent
    }

    companion object {
        private const val AUTHOR = "author"
        private const val GENRE = "genre"
        private const val PAGES = "pages"
        private const val BOOK = "book"
        private const val HTTP_WWW_BOOKSHELFEXAMPLE_COM = "http://www.bookshelfexample.com"
    }
}
package com.steelzack.test.drive.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlBookDomXpathParserManager extends XmlBookParserBuilder implements XmlBookParserManager {
	private static final String NAME = "@name";
	private static final String BOOKS = "/books/";
	private static final String BS = "bs:";
	private static final String AUTHOR = "author";
	private static final String GENRE = "genre";
	private static final String PAGES = "pages";
	private static final String BOOK = "book";
	private static final String HTTP_WWW_BOOKSHELFEXAMPLE_COM = "http://www.bookshelfexample.com";
	private Document document = null;
	private NodeList bookList = null;
	private XPath xpath = null;

	XmlBookDomXpathParserManager(InputStream ioStream) {
		super(ioStream);
	}

	@Override
	public String getBookTitle(int bookNumber) {
		try {
			return (String) xpath.evaluate(NAME, bookList.item(bookNumber), XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getNumberOfPages(int bookNumber) {
		final String stringPages = getValueFromNode(bookNumber, PAGES);
		return stringPages == null ? -1 : Integer.parseInt(stringPages);
	}

	@Override
	public String getGenre(int bookNumber) {
		return getValueFromNode(bookNumber, GENRE);
	}

	@Override
	public String getAuthor(int bookNumber) {
		return getValueFromNode(bookNumber, AUTHOR);
	}

	@Override
	public int getNumberOfBooks() {
		return bookList.getLength();
	}

	@Override
	public void init() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(getIoStream());
		xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			@Override
			public Iterator<String> getPrefixes(String namespaceURI) {
				return null;
			}

			@Override
			public String getPrefix(String namespaceURI) {
				return BS;
			}

			@Override
			public String getNamespaceURI(String prefix) {
				return HTTP_WWW_BOOKSHELFEXAMPLE_COM;
			}
		});
		bookList = (NodeList) xpath.evaluate(BOOKS + BS + BOOK, document, XPathConstants.NODESET);
	}

	private String getValueFromNode(int bookNumber, String nodeName) {
		try {
			return (String) xpath.evaluate(BS + nodeName, bookList.item(bookNumber), XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}
}

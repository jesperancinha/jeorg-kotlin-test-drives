package com.steelzack.test.drive.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlBookDomParserManager extends XmlBookParserBuilder {
	private static final String AUTHOR = "author";
	private static final String GENRE = "genre";
	private static final String PAGES = "pages";
	private static final String BOOK = "book";
	private static final String HTTP_WWW_BOOKSHELFEXAMPLE_COM = "http://www.bookshelfexample.com";
	private Document document = null;
	private NodeList bookList = null;

	public XmlBookDomParserManager(InputStream ioStream) {
		super(ioStream);
	}

	@Override
	public String getBookTitle(int bookNumber) {
		return bookList.item(bookNumber).getAttributes().getNamedItem("name").getNodeValue();
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
	public void init() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(getIoStream());
		bookList = document.getElementsByTagNameNS(HTTP_WWW_BOOKSHELFEXAMPLE_COM, BOOK);
	}

	private String getValueFromNode(int bookNumber, String nodeName) {
		final Node bookItem = bookList.item(bookNumber);
		Node currentNode = bookItem.getFirstChild();
		while (currentNode != null && //
				(currentNode.getNodeType() == Node.TEXT_NODE || //
						currentNode.getNamespaceURI().equals(HTTP_WWW_BOOKSHELFEXAMPLE_COM))
				&& //
				(currentNode.getLocalName() == null || (currentNode.getLocalName() != null && //
						!currentNode.getLocalName().equals(nodeName)))) {
			currentNode = currentNode.getNextSibling();
		}

		if (currentNode != null) {
			return currentNode.getTextContent();
		}
		return null;
	}
}

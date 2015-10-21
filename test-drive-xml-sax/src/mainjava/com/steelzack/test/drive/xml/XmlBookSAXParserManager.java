package com.steelzack.test.drive.xml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.steelzack.test.drive.books.xml.SAXBooksParser;

public class XmlBookSAXParserManager extends XmlBookParserBuilder implements XmlBookParserManager {
	private SAXBooksParser handler = null;

	XmlBookSAXParserManager(InputStream ioStream) {
		super(ioStream);
	}

	@Override
	public String getBookTitle(int bookNumber) {
		return handler.getBooks().get(bookNumber).getTitle();
	}

	@Override
	public int getNumberOfPages(int bookNumber) {
		return handler.getBooks().get(bookNumber).getPages();
	}

	@Override
	public String getGenre(int bookNumber) {
		return handler.getBooks().get(bookNumber).getGenre();
	}

	@Override
	public String getAuthor(int bookNumber) {
		return handler.getBooks().get(bookNumber).getAuthor();
	}

	@Override
	public int getNumberOfBooks() {
		return handler.getBooks().size();
	}

	@Override
	public void init() throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		handler = new SAXBooksParser();
		xmlReader.setContentHandler(handler);
		xmlReader.parse(new InputSource(getIoStream()));
	}
}

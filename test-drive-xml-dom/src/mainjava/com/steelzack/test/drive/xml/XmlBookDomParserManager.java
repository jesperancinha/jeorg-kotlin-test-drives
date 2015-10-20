package com.steelzack.test.drive.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlBookDomParserManager extends XmlBookParserBuilder implements XmlBookParserManager {
	private Document document = null;

	XmlBookDomParserManager(InputStream ioStream) {
		super(ioStream);
	}

	@Override
	public String getBookTitle(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfPages(int bookNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getGenre(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthor(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfBooks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(getIoStream());
	}
}

package com.steelzack.test.drive.books.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXBooksParser extends DefaultHandler {
	private static final String AUTHOR = "author";

	private static final String BOOK = "book";

	private static final String GENRE = "genre";

	private static final String PAGES = "pages";

	private static final String NAME = "name";

	private String lastValue = null;

	private Book currentBook = null;

	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (localName == BOOK) {
			currentBook = new Book();
			currentBook.setTitle(attributes.getValue(NAME));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if (localName.equals(PAGES)) {
			currentBook.setPages(Integer.parseInt(lastValue));
		} else if (localName.equals(GENRE)) {
			currentBook.setGenre(lastValue);
		} else if (localName.equals(AUTHOR)) {
			currentBook.setAuthor(lastValue);
		} else if (localName.equals(BOOK)) {
			books.add(currentBook);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		lastValue = new String(Arrays.copyOfRange(ch, start, start + length));
	}
}

package com.steelzack.test.drive.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.steelzack.test.drive.jaxb.books.xml.Books;

public class XmlBookJAXBParserManager extends XmlBookParserBuilder {
	private Books books;

	public XmlBookJAXBParserManager(InputStream ioStream) {
		super(ioStream);
	}

	@Override
	public String getBookTitle(int bookNumber) {
		return books.getBooks().get(bookNumber).getTitle();
	}

	@Override
	public int getNumberOfPages(int bookNumber) {
		return books.getBooks().get(bookNumber).getPages();
	}

	@Override
	public String getGenre(int bookNumber) {
		return books.getBooks().get(bookNumber).getGenre();
	}

	@Override
	public String getAuthor(int bookNumber) {
		return books.getBooks().get(bookNumber).getAuthor();
	}

	@Override
	public int getNumberOfBooks() {
		return books.getBooks().size();
	}

	@Override
	public void init() throws Exception {
		final JAXBContext context = JAXBContext.newInstance(Books.class);
		Unmarshaller u = context.createUnmarshaller();
		books = (Books) u.unmarshal(getIoStream());
	}

}

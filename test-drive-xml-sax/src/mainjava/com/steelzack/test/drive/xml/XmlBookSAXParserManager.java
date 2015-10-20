package com.steelzack.test.drive.xml;

import java.io.InputStream;

public class XmlBookSAXParserManager extends XmlBookParserBuilder implements XmlBookParserManager {

	XmlBookSAXParserManager(InputStream ioStream) {
		super(ioStream);
		// TODO Auto-generated constructor stub
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
	public void init() throws Exception {
		// TODO Auto-generated method stub

	}

}

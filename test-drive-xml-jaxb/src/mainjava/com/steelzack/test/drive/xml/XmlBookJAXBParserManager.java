package com.steelzack.test.drive.xml;

import java.io.InputStream;

public class XmlBookJAXBParserManager extends XmlBookParserBuilder implements XmlBookParserManager {

	XmlBookJAXBParserManager(InputStream ioStream) {
		super(ioStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getBookTitle(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNumberOfPages(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGenre(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDirector(int bookNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfBooks() {
		// TODO Auto-generated method stub
		return 0;
	}

}

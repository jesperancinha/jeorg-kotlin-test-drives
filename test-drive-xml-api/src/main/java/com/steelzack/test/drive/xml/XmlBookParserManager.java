package com.steelzack.test.drive.xml;

public interface XmlBookParserManager {
	String getBookTitle(int bookNumber);
	String getNumberOfPages(int bookNumber);
	String getGenre(int bookNumber);
	String getDirector(int bookNumber);
	int getNumberOfBooks();
}

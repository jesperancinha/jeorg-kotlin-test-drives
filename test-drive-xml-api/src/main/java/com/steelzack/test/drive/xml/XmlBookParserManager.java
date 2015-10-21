package com.steelzack.test.drive.xml;

public interface XmlBookParserManager {
	String getBookTitle(int bookNumber);

	int getNumberOfPages(int bookNumber);

	String getGenre(int bookNumber);

	String getAuthor(int bookNumber);

	int getNumberOfBooks();

	void init() throws Exception;
}

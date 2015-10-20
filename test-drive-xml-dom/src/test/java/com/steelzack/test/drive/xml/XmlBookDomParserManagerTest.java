package com.steelzack.test.drive.xml;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class XmlBookDomParserManagerTest {

	@Test
	public void testGetBookTitle() throws Exception {
		final InputStream inputStream = getClass().getResourceAsStream("testXmlFileForDom.xml");
		final XmlBookParserManager pm = new XmlBookDomParserManager(inputStream);
		pm.init();

		final String bookTitle0 = pm.getBookTitle(0);
		final String bookTitle1 = pm.getBookTitle(1);
		final String bookTitle2 = pm.getBookTitle(2);
		final String bookTitle3 = pm.getBookTitle(3);

		assertEquals("The Hobbit 0", bookTitle0);
		assertEquals("The Hobbit 1", bookTitle1);
		assertEquals("The Hobbit 2", bookTitle2);
		assertEquals("The Hobbit 3", bookTitle3);
	}

	@Test
	public void testGetNumberOfPages() throws Exception {
		final InputStream inputStream = getClass().getResourceAsStream("testXmlFileForDom.xml");
		final XmlBookParserManager pm = new XmlBookDomParserManager(inputStream);
		pm.init();

		final int bookPages0 = pm.getNumberOfPages(0);
		final int bookPages1 = pm.getNumberOfPages(1);
		final int bookPages2 = pm.getNumberOfPages(2);
		final int bookPages3 = pm.getNumberOfPages(3);

		assertEquals(123, bookPages0);
		assertEquals(456, bookPages1);
		assertEquals(789, bookPages2);
		assertEquals(101112, bookPages3);
	}

	@Test
	public void testGetGenre() throws Exception {
		final InputStream inputStream = getClass().getResourceAsStream("testXmlFileForDom.xml");
		final XmlBookParserManager pm = new XmlBookDomParserManager(inputStream);
		pm.init();

	}

	@Test
	public void testGetDirector() throws Exception {
		final InputStream inputStream = getClass().getResourceAsStream("testXmlFileForDom.xml");
		final XmlBookParserManager pm = new XmlBookDomParserManager(inputStream);
		pm.init();

	}

	@Test
	public void testGetNumberOfBooks() throws Exception {
		final InputStream inputStream = getClass().getResourceAsStream("testXmlFileForDom.xml");
		final XmlBookParserManager pm = new XmlBookDomParserManager(inputStream);
		pm.init();

	}
}

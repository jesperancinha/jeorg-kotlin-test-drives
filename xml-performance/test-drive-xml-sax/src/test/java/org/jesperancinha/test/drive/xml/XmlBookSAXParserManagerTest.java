package org.jesperancinha.test.drive.xml;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class XmlBookSAXParserManagerTest {
	@Test
	public void testGetBookTitle() throws Exception {
		final var inputStream = getClass().getResourceAsStream("testXmlFile.xml");
		final var pm = new XmlBookSAXParserManager(inputStream);
		pm.init();

		final var bookTitle0 = pm.getBookTitle(0);
		final var bookTitle1 = pm.getBookTitle(1);
		final var bookTitle2 = pm.getBookTitle(2);
		final var bookTitle3 = pm.getBookTitle(3);

		assertEquals("The Hobbit 0", bookTitle0);
		assertEquals("The Hobbit 1", bookTitle1);
		assertEquals("The Hobbit 2", bookTitle2);
		assertEquals("The Hobbit 3", bookTitle3);
	}

	@Test
	public void testGetNumberOfPages() throws Exception {
		final var inputStream = getClass().getResourceAsStream("testXmlFile.xml");
		final var pm = new XmlBookSAXParserManager(inputStream);
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
		final InputStream inputStream = getClass().getResourceAsStream("testXmlFile.xml");
		final XmlBookParserManager pm = new XmlBookSAXParserManager(inputStream);
		pm.init();

		final String bookGenre0 = pm.getGenre(0);
		final String bookGenre1 = pm.getGenre(1);
		final String bookGenre2 = pm.getGenre(2);
		final String bookGenre3 = pm.getGenre(3);

		assertEquals("Action", bookGenre0);
		assertEquals("Comedy", bookGenre1);
		assertEquals("War", bookGenre2);
		assertEquals("Drama", bookGenre3);
	}

	@Test
	public void testGetAuthor() throws Exception {
		final var inputStream = getClass().getResourceAsStream("testXmlFile.xml");
		final var pm = new XmlBookSAXParserManager(inputStream);
		pm.init();

		final var bookAuthor0 = pm.getAuthor(0);
		final var bookAuthor1 = pm.getAuthor(1);
		final var bookAuthor2 = pm.getAuthor(2);
		final var bookAuthor3 = pm.getAuthor(3);

		assertEquals("J.R.R. Tolkien 1", bookAuthor0);
		assertEquals("J.R.R. Tolkien 2", bookAuthor1);
		assertEquals("J.R.R. Tolkien 3", bookAuthor2);
		assertEquals("J.R.R. Tolkien 4", bookAuthor3);
	}

	@Test
	public void testGetNumberOfBooks() throws Exception {
		final var inputStream = getClass().getResourceAsStream("testXmlFile.xml");
		final var pm = new XmlBookSAXParserManager(inputStream);
		pm.init();

		final int nPages = pm.getNumberOfBooks();

		assertEquals(4, nPages);
	}
}

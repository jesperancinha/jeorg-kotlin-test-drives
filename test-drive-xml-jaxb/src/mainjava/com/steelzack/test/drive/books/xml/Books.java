package com.steelzack.test.drive.books.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books", namespace = "")
public class Books {

	@XmlElement(name = "book")
	public List<Book> book;

	public List<Book> getBooks() {
		return book;
	}
}

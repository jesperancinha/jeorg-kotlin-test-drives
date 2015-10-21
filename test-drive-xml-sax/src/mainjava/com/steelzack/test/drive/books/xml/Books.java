package com.steelzack.test.drive.books.xml;

import java.util.ArrayList;
import java.util.List;

public class Books {

	public List<Book> book;

	public Books() {
		super();
		this.init();
	}

	private void init() {
		book = new ArrayList<>();
	}

	public List<Book> getBooks() {
		return book;
	}
}

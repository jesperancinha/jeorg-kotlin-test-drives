package org.jesperancinha.test.drive.sax.books;

public class Book {
	private String title;
	private Integer pages;
	private String genre;
	private String author;

	public String getAuthor() {
		return author;
	}

	public Integer getPages() {
		return pages;
	}

	public String getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}

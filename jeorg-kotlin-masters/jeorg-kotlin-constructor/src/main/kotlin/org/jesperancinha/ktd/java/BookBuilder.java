package org.jesperancinha.ktd.java;

public class BookBuilder {

    private Long pages;

    public BookBuilder withPages(Long pages) {
        this.pages = pages;
        return this;
    }

    public Book build() {
        return new Book(pages);
    }
}

package org.jesperancinha.ktd.java;

import org.hibernate.validator.constraints.Range;

public class Book {

    @Range(min = 15, max = 50)
    private Long pages;

    public Book(Long pages) {
        this.pages = pages;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }
}

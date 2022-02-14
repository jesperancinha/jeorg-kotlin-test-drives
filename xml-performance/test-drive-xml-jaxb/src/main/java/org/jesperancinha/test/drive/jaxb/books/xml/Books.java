package org.jesperancinha.test.drive.jaxb.books.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books",
        namespace = "")
public class Books {

    @XmlElement(name = "book")
    public List<Book> book;

    public List<Book> getBooks() {
        return book;
    }
}

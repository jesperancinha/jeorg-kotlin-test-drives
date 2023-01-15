package org.jesperancinha.test.drive.jaxb.books.xml

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books", namespace = "")
class Books {
    @XmlElement(name = "book", namespace = "http://www.joaofilipesabinoesperancinha.nl")
    lateinit var books: List<Book>
}
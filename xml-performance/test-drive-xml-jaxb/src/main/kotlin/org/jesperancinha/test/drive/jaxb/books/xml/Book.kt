package org.jesperancinha.test.drive.jaxb.books.xml

import jakarta.xml.bind.annotation.*

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "book", namespace = "http://www.joaofilipesabinoesperancinha.nl")
open class Book {
    @XmlAttribute(name = "name")
    var title: String? = null

    @XmlElement(name = "pages", namespace = "http://www.joaofilipesabinoesperancinha.nl")
    val pages: Int = 0

    @XmlElement(name = "genre", namespace = "http://www.joaofilipesabinoesperancinha.nl")
    var genre: String? = null

    @XmlElement(name = "author", namespace = "http://www.joaofilipesabinoesperancinha.nl")
    var author: String? = null
}
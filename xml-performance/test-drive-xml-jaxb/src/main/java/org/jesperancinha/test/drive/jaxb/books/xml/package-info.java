@XmlSchema(
        namespace = "http://www.bookshelexample.com",
        elementFormDefault = QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "bs",
                        namespaceURI = "http://www.bookshelexample.com")
        })
package org.jesperancinha.test.drive.jaxb.books.xml;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlSchema;

import static jakarta.xml.bind.annotation.XmlNsForm.QUALIFIED;

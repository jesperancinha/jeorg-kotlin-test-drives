@javax.xml.bind.annotation.XmlSchema(
    namespace = "http://www.bookshelexample.com",
    elementFormDefault = QUALIFIED,
    xmlns = {
    		@XmlNs(prefix = "bs", namespaceURI="http://www.bookshelexample.com")
    })
package com.steelzack.test.drive.jaxb.books.xml;

import static javax.xml.bind.annotation.XmlNsForm.QUALIFIED;

import javax.xml.bind.annotation.XmlNs;

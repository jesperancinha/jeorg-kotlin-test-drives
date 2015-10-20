package com.steelzack.test.drive.xml;

import java.io.InputStream;

public abstract class XmlBookParserBuilder {
	private InputStream ioStream;

	XmlBookParserBuilder(InputStream ioStream) {
		this.ioStream = ioStream;
	}

	InputStream getIoStream() {
		return ioStream;
	}

	abstract void loadConfiguration() throws Exception;
}

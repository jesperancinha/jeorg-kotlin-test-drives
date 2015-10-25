package com.steelzack.test.drive.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class XmlBookParserBuilder implements XmlBookParserManager {
	private static final Logger LOG = Logger.getLogger(XmlBookParserBuilder.class.getName());
	private InputStream ioStream;

	XmlBookParserBuilder(InputStream ioStream) {
		this.ioStream = ioStream;
	}

	InputStream getIoStream() {
		return ioStream;
	}

	public void closeStream() {
		try {
			ioStream.close();
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Stream didn't close!", e);
		}
	}
}

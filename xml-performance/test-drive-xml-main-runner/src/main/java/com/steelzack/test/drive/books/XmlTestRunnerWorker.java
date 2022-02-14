package com.steelzack.test.drive.books;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.steelzack.test.drive.xml.XmlBookDomParserManager;
import com.steelzack.test.drive.xml.XmlBookDomXpathParserManager;
import com.steelzack.test.drive.xml.XmlBookJAXBParserManager;
import com.steelzack.test.drive.xml.XmlBookParserBuilder;
import com.steelzack.test.drive.xml.XmlBookParserManager;
import com.steelzack.test.drive.xml.XmlBookSAXParserManager;

public class XmlTestRunnerWorker {
	private Logger LOG = Logger.getLogger(XmlTestRunner.class.getName());

	private String xmlFile;
	private Integer nIterations;
	private boolean noop;

	public XmlTestRunnerWorker() {
		// CDI
	}

	public XmlTestRunnerWorker(String[] args) throws CmdLineException {
		final XmlTestRunnerOptions options = createNewOptions();
		final CmdLineParser parser = new CmdLineParser(options);
		parser.parseArgument(args);

		xmlFile = options.getBookTestFile();
		nIterations = options.getNumberOfIterations();
		noop = options.isNoop();
	}

	public XmlTestRunnerOptions createNewOptions() {
		return new XmlTestRunnerOptions();
	}

	public boolean runTests() throws Exception {
		callParseIterations(getnIterations(), XmlBookManagerType.DOM);
		callParseIterations(getnIterations(), XmlBookManagerType.DOM_XPATH);
		callParseIterations(getnIterations(), XmlBookManagerType.SAX);
		callParseIterations(getnIterations(), XmlBookManagerType.JAXB);
		return true;
	}

	InputStream getInputStreamFromFile() throws FileNotFoundException {
		return new FileInputStream(new File(xmlFile));
	}

	private void callParseIterations(Integer nIterations, XmlBookManagerType type) throws Exception {
		LOG.log(Level.INFO, "Start of test for {0} with {1} iterations", new Object[] { type, nIterations });
		final Long timeStampNow = System.nanoTime();
		for (int i = 0; i < nIterations; i++) {
			try {
				final InputStream ioStream = getInputStreamFromFile();
				final XmlBookParserManager manager = getManagerFor(ioStream, type);
				manager.init();
			} catch (Exception e) {
				throw new RuntimeException(String.format("Test failed on iteration: %d", i), e);
			}
		}
		LOG.log(Level.INFO, "End of test for {0} with {1} iterations. Took {2} ms",
				new Object[] { type, nIterations, (System.nanoTime() - timeStampNow) / 1000000 });
	}

	private XmlBookParserBuilder getManagerFor(final InputStream ioStream, XmlBookManagerType type) {
		switch (type) {
		case DOM:
			return new XmlBookDomParserManager(ioStream);
		case DOM_XPATH:
			return new XmlBookDomXpathParserManager(ioStream);
		case JAXB:
			return new XmlBookJAXBParserManager(ioStream);
		case SAX:
			return new XmlBookSAXParserManager(ioStream);
		}
		return null;
	}

	public Integer getnIterations() {
		return nIterations;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public boolean isNoop() {
		return noop;
	}
}

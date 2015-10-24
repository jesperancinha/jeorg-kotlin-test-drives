package com.steelzack.test.drive.books;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class XmlTestRunnerWorker {

	private String xmlFile;
	private Integer nIterations;
	private boolean noop;

	public XmlTestRunnerWorker(String[] args) throws CmdLineException {
		final XmlTestRunnerOptions options = new XmlTestRunnerOptions();
		final CmdLineParser parser = new CmdLineParser(options);
		parser.parseArgument(args);

		xmlFile = options.getBookTestFile();
		nIterations = options.getNumberOfIterations();
		noop = options.isNoop();
	}

	public void runTests() {

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

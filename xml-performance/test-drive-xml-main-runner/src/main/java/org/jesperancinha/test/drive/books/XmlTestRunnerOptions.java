package org.jesperancinha.test.drive.books;

import org.kohsuke.args4j.Option;

public class XmlTestRunnerOptions {

	@Option(name = "--book-test-file", aliases = "-b", required = true)
	private String bookTestFile;

	@Option(name = "--number-of-iterations", aliases = "-n", required = true)
	private Integer numberOfIterations;

	@Option(name = "--noop", required = false)
	private boolean noop = false;

	public String getBookTestFile() {
		return bookTestFile;
	}

	public Integer getNumberOfIterations() {
		return numberOfIterations;
	}

	public boolean isNoop() {
		return noop;
	}
}

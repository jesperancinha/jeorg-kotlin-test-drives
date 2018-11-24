package com.jesperancinha.performance.inputgenerator;

import org.kohsuke.args4j.Option;

public class InputGeneratorOptions {
	@Option(name = "-n", aliases = "--number-of-elements", required = true)
	private int numberOfElements;

	@Option(name = "-d", aliases = "--directory", required = false)
	private String folder = "/tmp";

	@Option(name = "-f", aliases = "--filename", required = true)
	private String filename;

	@Option(name = "-i", aliases = "--increments", required = false)
	private int increments = 5;

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public String getFolder() {
		return folder;
	}

	public String getFilename() {
		return filename;
	}

	public int getIncrements() {
		return increments;
	}
}

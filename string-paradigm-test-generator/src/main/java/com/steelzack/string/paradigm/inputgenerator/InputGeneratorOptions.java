package com.steelzack.string.paradigm.inputgenerator;

import org.kohsuke.args4j.Option;

public class InputGeneratorOptions {
	@Option(name = "-n", aliases = "--number-of-elements", required = true)
	private int numberOfElements;

	@Option(name = "-d", aliases = "--directory", required = false)
	private String folder = "/tmp";

	@Option(name = "-f", aliases = "--filename", required = true)
	private String filename;

	/**
	 * A plain text file with the film year data
	 */
	@Option(name = "-my", aliases = "--movie-year", required = true)
	private String movieYearFile;

	/**
	 * A plain text file with the film location data
	 */
	@Option(name = "-ml", aliases = "--movie-location", required = true)
	private String movieLocationFile;

	/**
	 * A plain text file with the film location data
	 */
	@Option(name = "-mg", aliases = "--movie-genre", required = true)
	private String movieGenreFile;

	/**
	 * A plain text file with the film director data
	 */
	@Option(name = "-md", aliases = "--movie-director", required = true)
	private String movieDirectorFile;

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

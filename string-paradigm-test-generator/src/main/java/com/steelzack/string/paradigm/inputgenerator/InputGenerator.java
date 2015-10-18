package com.steelzack.string.paradigm.inputgenerator;

import java.io.IOException;
import java.util.logging.Logger;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class InputGenerator {
	private static final Logger LOG = Logger.getLogger(InputGenerator.class.getName());

	public static void main(String[] args) throws CmdLineException, IOException {
		final InputGeneratorOptions options = new InputGeneratorOptions();
		final CmdLineParser parser = new CmdLineParser(options);
		parser.parseArgument(args);
	}
}

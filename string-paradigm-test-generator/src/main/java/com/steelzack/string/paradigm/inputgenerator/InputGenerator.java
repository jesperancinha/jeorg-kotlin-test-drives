package com.steelzack.string.paradigm.inputgenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
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

	List<String> getFullFileList(InputStream io) throws IOException {
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(io, Charset.defaultCharset()))) {
			String testRead = br.readLine();
			while (testRead != null) {
				list.add(testRead);
				testRead = br.readLine();
			}
		}
		return list;
	}
}

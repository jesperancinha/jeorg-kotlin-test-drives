package com.performance.inputgenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class InputGenerator {
	private static final Random random = new Random();

	public static void main(String[] args) throws CmdLineException, IOException {
		final InputGeneratorOptions options = new InputGeneratorOptions();
		final CmdLineParser parser = new CmdLineParser(options);
		parser.parseArgument(args);

		createGenerationFile(options.getNumberOfElements(), options.getFolder(), options.getFilename(),
				options.getIncrements());
	}

	private static void createGenerationFile(int numberOfElements, String folder, String fileName, int maxIncrements)
			throws IOException {
		final File directory = new File(folder);
		directory.mkdirs();
		final File file = new File(directory, fileName);
		file.createNewFile();

		try (BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(file))) {

			int currentNumber = getNextNumber(0, maxIncrements);
			bo.write(getFormattedNumber(currentNumber));

			for (int i = numberOfElements; i > 0; i--) {
				currentNumber = getNextNumber(currentNumber, maxIncrements);
				bo.write(getFormattedNumber(currentNumber));
			}
		}
	}

	private static byte[] getFormattedNumber(int currentNumber) {
		return (", " + currentNumber).getBytes(Charset.defaultCharset());
	}

	private static int getNextNumber(int current, int increments) {
		return current + random.nextInt(increments);
	}
}

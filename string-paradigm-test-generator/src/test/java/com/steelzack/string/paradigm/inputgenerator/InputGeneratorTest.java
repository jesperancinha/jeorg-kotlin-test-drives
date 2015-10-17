package com.steelzack.string.paradigm.inputgenerator;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class InputGeneratorTest {

	@Test
	public void testInputGenerator() throws IOException, InterruptedException {
		final String testFilename = "testInputGenerator.txt";
		final Runtime rt = Runtime.getRuntime();
		final Process pr = rt
				.exec("java -jar build/one-performance-input-test-generator.jar -n 100 -f \"" + testFilename + "\"");
		final int retVal = pr.waitFor();

		assertEquals(0, retVal);

		final File f = new File("/tmp", testFilename);
		try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
			final String test = bf.readLine();
			final String[] splitTable = test.split(",");
			// Tests if the first element is an integer
			Integer.parseInt(splitTable[0].trim());
			// Tests if the last element is an integer
			Integer.parseInt(splitTable[splitTable.length - 1].trim());
			final int resultTestNumber = splitTable.length;
			assertEquals(100, resultTestNumber);
		}
	}
}

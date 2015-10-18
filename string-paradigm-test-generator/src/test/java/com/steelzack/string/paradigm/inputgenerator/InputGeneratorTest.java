package com.steelzack.string.paradigm.inputgenerator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class InputGeneratorTest {

	@Test
	public void testInputGenerator() throws IOException, InterruptedException {
		final String testFilename = "testInputGenerator.txt";
		final Runtime rt = Runtime.getRuntime();
		final Process pr = rt.exec(
				"java -jar build/one-string-paradigm-input-test-generator.jar -n 100 -f \"" + testFilename + "\"");
		final int retVal = pr.waitFor();

		assertEquals(0, retVal);
	}
}

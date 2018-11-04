package com.performance.inputgenerator;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

public class InputGeneratorTest {

	private static final Logger logger = Logger.getLogger(InputGeneratorTest.class.getName());

	@Test
	public void testInputGenerator() throws IOException, InterruptedException {
		final String testFilename = "/testInputGenerator.txt";
		final Runtime rt = Runtime.getRuntime();
		final Process pr = rt
				.exec("java -jar build/libs/performance-input-test-generator-all-0.0.0-SNAPSHOT.jar -n 100 -f " + testFilename);
		final int retVal = pr.waitFor();

		logger.info(IOUtils.toString(pr.getErrorStream(), Charset.defaultCharset()));
		logger.info(IOUtils.toString(pr.getInputStream(), Charset.defaultCharset()));

		assertEquals(0, retVal);

		final File f = new File("/tmp", testFilename);
		try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
			final String test = bf.readLine();
			final String[] splitTable = test.split(",");
			// Tests if the first element is an integer
			parseInt(splitTable[0].trim());
			// Tests if the last element is an integer
			parseInt(splitTable[splitTable.length - 1].trim());
			final int resultTestNumber = splitTable.length;
			assertEquals(100, resultTestNumber);
		}
	}

	@Test
	public void main() throws IOException, CmdLineException {
		InputGenerator.main(ImmutableList.of("-n", "100", "-f", "/testInputGenerator.txt").toArray(new String[0]));
	}
}

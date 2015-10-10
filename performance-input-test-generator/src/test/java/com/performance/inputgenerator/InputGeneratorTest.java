package com.performance.inputgenerator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class InputGeneratorTest {

	@Ignore
	@Test
	public void testInputGenerator() throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("java -jar one-performance-input-test-generator.jar -n 100 -f \"testInputGenerator.txt\"");
		int retVal = pr.waitFor();

		assertEquals(0, retVal);
	}
}

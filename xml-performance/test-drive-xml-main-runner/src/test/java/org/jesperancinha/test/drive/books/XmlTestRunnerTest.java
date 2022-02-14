package org.jesperancinha.test.drive.books;

import java.util.Random;

import org.junit.Test;

public class XmlTestRunnerTest {
	private static final Random random = new Random();

	@Test
	public void testMain() throws Exception {
		final String testFile = "testFile" + Math.random();
		final String testIteration = "" + random.nextInt();
		final String[] args = new String[] { //
				"-n", //
				testIteration, //
				"-b", //
				testFile, //
				"--noop" //
		};
		XmlTestRunner.main(args);
	}

	@Test
	public void testMain_aliases() throws Exception {
		final String testFile = "testFile" + Math.random();
		final String testIteration = "" + random.nextInt();
		final String[] args = new String[] { //
				"--number-of-iterations", //
				testIteration, //
				"--book-test-file", //
				testFile, //
				"--noop" //
		};
		XmlTestRunner.main(args);
	}

}

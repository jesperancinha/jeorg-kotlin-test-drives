package com.steelzack.test.drive.books;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class XmlTestRunnerWorkerTest {
	private static final Random random = new Random();

	@Test
	public void testXmlTestRunnerWorker() throws Exception {
		final String testFile = "testFile" + Math.random();
		final String testIteration = "" + random.nextInt();
		final String[] args = new String[] { //
				"-n", //
				testIteration, //
				"-b", //
				testFile, //
				"--noop" //
		};

		final XmlTestRunnerWorker runner = new XmlTestRunnerWorker(args);

		assertEquals(testIteration, runner.getnIterations().toString());
		assertEquals(testFile, runner.getXmlFile());
	}
}

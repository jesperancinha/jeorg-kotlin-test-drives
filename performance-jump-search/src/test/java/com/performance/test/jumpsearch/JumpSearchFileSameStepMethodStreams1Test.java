package com.performance.test.jumpsearch;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.junit.Test;

public class JumpSearchFileSameStepMethodStreams1Test {
	@Test
	public void testGetNumberIndexFromArray() throws Exception {
		final String testString = "1, 2, 8, 10, 20, 23, 27, 50, 90";
		final InputStream testInputStream = new ByteArrayInputStream(testString.getBytes(Charset.defaultCharset()));
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(20, testInputStream);

		assertEquals(4, result);
	}

	@Test
	public void testGetNumberIndexFromArray_limit() throws Exception {
		final String testString = "1, 2, 8, 10, 20, 23, 27, 50, 90";
		final InputStream testInputStream = new ByteArrayInputStream(testString.getBytes(Charset.defaultCharset()));
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(90, testInputStream);

		assertEquals(8, result);
	}

	@Test
	public void testGetNumberIndexFromArray_begining() throws Exception {
		final String testString = "1, 2, 8, 10, 20, 23, 27, 50, 90";
		final InputStream testInputStream = new ByteArrayInputStream(testString.getBytes(Charset.defaultCharset()));
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(1, testInputStream);

		assertEquals(0, result);
	}
}

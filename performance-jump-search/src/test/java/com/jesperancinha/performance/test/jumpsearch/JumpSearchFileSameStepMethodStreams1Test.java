package com.jesperancinha.performance.test.jumpsearch;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JumpSearchFileSameStepMethodStreams1Test {
	@Test
	public void testGetNumberIndexFromArray() {
		final String testString = "1, 2, 8, 10, 20, 23, 27, 50, 90";
		final InputStream testInputStream = new ByteArrayInputStream(testString.getBytes(Charset.defaultCharset()));
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(20, testInputStream);

		assertEquals(4, result);
	}

	@Test
	public void testGetNumberIndexFromArray_limit() {
		final String testString = "1, 2, 8, 10, 20, 23, 27, 50, 90";
		final InputStream testInputStream = new ByteArrayInputStream(testString.getBytes(Charset.defaultCharset()));
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(90, testInputStream);

		assertEquals(8, result);
	}

	@Test
	public void testGetNumberIndexFromArray_begining() {
		final String testString = "1, 2, 8, 10, 20, 23, 27, 50, 90";
		final InputStream testInputStream = new ByteArrayInputStream(testString.getBytes(Charset.defaultCharset()));
		final int result = new JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(1, testInputStream);

		assertEquals(0, result);
	}
}

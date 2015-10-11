package com.performance.test.jumpsearch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * This jump algorithm as described in:
 * http://www.stoimen.com/blog/2011/12/12/computer-algorithms-jump-search/
 * Tries to calculate first generic step jump in order to search
 * Unlike the binary search model, it assumes that is better to jump a step and then perform the already known binary search
 * As for this implementation it is being created without consideration on performance using an IJW (It just works) model
 */
public class JumpSearchFileSameStepMethod0Test {
	@Test
	public void testGetNumberIndexFromArray() throws Exception {
		final int[] completeList = new int[] { 1, 2, 8, 10, 20, 23, 27, 50, 90 };
		final int result = JumpSearchFileSameStepMethod0.getNumberIndexFromArray(20, completeList);

		assertEquals(4, result);
	}

	@Test
	public void testGetNumberIndexFromArray_limit() throws Exception {
		final int[] completeList = new int[] { 1, 2, 8, 10, 20, 23, 27, 50, 90 };
		final int result = JumpSearchFileSameStepMethod0.getNumberIndexFromArray(90, completeList);

		assertEquals(8, result);
	}

	@Test
	public void testGetNumberIndexFromArray_begining() throws Exception {
		final int[] completeList = new int[] { 1, 2, 8, 10, 20, 23, 27, 50, 90 };
		final int result = JumpSearchFileSameStepMethod0.getNumberIndexFromArray(1, completeList);

		assertEquals(0, result);
	}
}

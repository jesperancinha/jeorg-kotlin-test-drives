package com.jesperancinha.performance.test.jumpsearch

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
 * This jump algorithm as described in:
 * http://www.stoimen.com/blog/2011/12/12/computer-algorithms-jump-search/
 * Tries to calculate first generic step jump in order to search
 * Unlike the binary search model, it assumes that is better to jump a step and then perform the already known binary search
 * As for this implementation it is being created without consideration on performance using an IJW (It just works) model
 */
class JumpSearchFileSameStepMethod0Test {
    @Test
    fun testGetNumberIndexFromArray() {
        val completeList = intArrayOf(1, 2, 8, 10, 20, 23, 27, 50, 90)
        val result = JumpSearchFileSameStepMethod0().getNumberIndexFromArray(20, completeList)
        Assertions.assertEquals(4, result)
    }

    @Test
    fun testGetNumberIndexFromArray_limit() {
        val completeList = intArrayOf(1, 2, 8, 10, 20, 23, 27, 50, 90)
        val result = JumpSearchFileSameStepMethod0().getNumberIndexFromArray(90, completeList)
        Assertions.assertEquals(8, result)
    }

    @Test
    fun testGetNumberIndexFromArray_begining() {
        val completeList = intArrayOf(1, 2, 8, 10, 20, 23, 27, 50, 90)
        val result = JumpSearchFileSameStepMethod0().getNumberIndexFromArray(1, completeList)
        Assertions.assertEquals(0, result)
    }
}
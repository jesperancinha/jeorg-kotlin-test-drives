package com.jesperancinha.performance.test.jumpsearch

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.Charset

class JumpSearchFileSameStepMethodStreams0Test {
    @Test
    fun testGetNumberIndexFromArray() {
        val testString = "1, 2, 8, 10, 20, 23, 27, 50, 90"
        val testInputStream: InputStream = ByteArrayInputStream(testString.toByteArray(Charset.defaultCharset()))
        val result = JumpSearchFileSameStepMethodStreams0().getNumberIndexFromArray(20, testInputStream)
        Assertions.assertEquals(4, result)
    }

    @Test
    fun testGetNumberIndexFromArray_limit() {
        val testString = "1, 2, 8, 10, 20, 23, 27, 50, 90"
        val testInputStream: InputStream = ByteArrayInputStream(testString.toByteArray(Charset.defaultCharset()))
        val result = JumpSearchFileSameStepMethodStreams0().getNumberIndexFromArray(90, testInputStream)
        Assertions.assertEquals(8, result)
    }

    @Test
    fun testGetNumberIndexFromArray_begining() {
        val testString = "1, 2, 8, 10, 20, 23, 27, 50, 90"
        val testInputStream: InputStream = ByteArrayInputStream(testString.toByteArray(Charset.defaultCharset()))
        val result = JumpSearchFileSameStepMethodStreams0().getNumberIndexFromArray(1, testInputStream)
        Assertions.assertEquals(0, result)
    }
}
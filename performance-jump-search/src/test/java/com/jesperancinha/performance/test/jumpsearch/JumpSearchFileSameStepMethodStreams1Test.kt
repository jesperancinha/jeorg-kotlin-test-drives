package com.jesperancinha.performance.test.jumpsearch

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.Charset

class JumpSearchFileSameStepMethodStreams1Test {
    @Test
    fun testGetNumberIndexFromArray() {
        val testString = "1, 2, 8, 10, 20, 23, 27, 50, 90"
        val testInputStream: InputStream = ByteArrayInputStream(testString.toByteArray(Charset.defaultCharset()))
        val result = JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(20, testInputStream)
        result shouldBe 4
    }

    @Test
    fun testGetNumberIndexFromArray_limit() {
        val testString = "1, 2, 8, 10, 20, 23, 27, 50, 90"
        val testInputStream: InputStream = ByteArrayInputStream(testString.toByteArray(Charset.defaultCharset()))
        val result = JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(90, testInputStream)
        result shouldBe 8
    }

    @Test
    fun testGetNumberIndexFromArray_begining() {
        val testString = "1, 2, 8, 10, 20, 23, 27, 50, 90"
        val testInputStream: InputStream = ByteArrayInputStream(testString.toByteArray(Charset.defaultCharset()))
        val result = JumpSearchFileSameStepMethodStreams1().getNumberIndexFromArray(1, testInputStream)
        result shouldBe 0
    }
}
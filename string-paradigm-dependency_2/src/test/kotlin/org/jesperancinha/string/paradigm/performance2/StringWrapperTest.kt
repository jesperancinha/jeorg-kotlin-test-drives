package org.jesperancinha.string.paradigm.performance2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.nio.charset.Charset

private const val STRING_SAYING1 =
    "I have no idea how to talk with Fenestev anymore. It seems like he just interprets or wants to interpret things in a very negative way"

private const val SAYING_1 =
    "Lars Pereira: $STRING_SAYING1"

/**
 * Lar Pereira is a fictional character used in many of my repositories and his sayings are used here to illustrate how the wrapper works
 * Fenestev is another fictional character and is the boss of Lars Pereira
 */
class StringWrapperTest {

    private val stringWrapper = StringWrapper(SAYING_1)

    @Test
    fun `should create to string correctly`() {
        stringWrapper.toString() shouldBe SAYING_1
    }

    @Test
    fun `should get the correct bytes given start and end indexes`() {
        String(stringWrapper.getBytes(14, 148), Charset.defaultCharset()) shouldBe STRING_SAYING1
    }
}
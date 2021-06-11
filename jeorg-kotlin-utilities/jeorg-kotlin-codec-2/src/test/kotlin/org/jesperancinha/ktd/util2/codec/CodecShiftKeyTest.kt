package org.jesperancinha.ktd.util2.codec

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CodecShiftKeyTest {

    @Test
    fun testEncode() {
        val encoded = CodecShiftKey.encode("anaconda", 25)
        assertEquals("zmzbnmcz", encoded)
    }

    @Test
    fun testDecode() {
        val encoded = CodecShiftKey.decode("zmzbnmcz", 25)
        assertEquals("anaconda", encoded)
    }
}

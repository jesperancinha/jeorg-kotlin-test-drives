package org.jesperancinha.ktd.util1.decoder

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Codec16AsciiTest {

    @Test
    fun testEncodeA() {
        val encoded = Codec16Ascii.encode('A')
        assertEquals(1040, encoded)
    }

    @Test
    fun testDecodeA() {
        val encoded = Codec16Ascii.decode(1040)
        assertEquals('A', encoded)
    }
}
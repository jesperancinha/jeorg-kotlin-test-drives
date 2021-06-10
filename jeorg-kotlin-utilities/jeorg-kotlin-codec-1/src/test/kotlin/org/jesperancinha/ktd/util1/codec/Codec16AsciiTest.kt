package org.jesperancinha.ktd.util1.codec

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
        val decoded = Codec16Ascii.decode(1040)
        assertEquals('A', decoded)
    }

    @Test
    fun testDecodeString() {
        val decoded = Codec16Ascii.decode(
            "1344 1664 1616 512 1664 1680 1728 1728 1840 512 1552 " +
                    "1824 1616 512 1552 1728 1680 1888 1616 512 1904 1680 1856 " +
                    "1664 512 1856 1664 1616 512 1840 1776 1872 1760 1600 512 " +
                    "1776 1632 512 1744 1872 1840 1680 1584"
        )
        assertEquals("The hills are alive with the sound of music", decoded)
    }

    @Test
    fun testEncodeString() {
        val encoded = Codec16Ascii.encode("The hills are alive with the sound of music")
        assertEquals(
            "1344 1664 1616 512 1664 1680 1728 1728 1840 512 1552 " +
                    "1824 1616 512 1552 1728 1680 1888 1616 512 1904 1680 " +
                    "1856 1664 512 1856 1664 1616 512 1840 1776 1872 1760 " +
                    "1600 512 1776 1632 512 1744 1872 1840 1680 1584",
            encoded
        )
    }


}
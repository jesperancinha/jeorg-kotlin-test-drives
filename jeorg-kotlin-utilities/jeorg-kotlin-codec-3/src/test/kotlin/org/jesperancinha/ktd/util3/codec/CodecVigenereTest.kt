package org.jesperancinha.ktd.util3.codec


import kotlin.test.Test
import kotlin.test.assertEquals

internal class CodecVigenereTest {

    @Test
    fun testEncode() {
        val encoded = CodecVigenere.encode("siliconpartsaremadefortoys", "anaconda")
        assertEquals("svlkqbqpaetuoehmaqehcewoyf", encoded)
    }

    @Test
    fun testDecode() {
        val encoded = CodecVigenere.decode("svlkqbqpaetuoehmaqehcewoyf", "anaconda")
        assertEquals("siliconpartsaremadefortoys", encoded)
    }
}
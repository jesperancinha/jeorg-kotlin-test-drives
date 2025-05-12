package org.jesperancinha.string.paradigm.performance1

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

class StringWrapper(string: String) {
    private val bytes: ByteArray by lazy { string.toByteArray(charset("ISO-8859-1")) }
    override fun toString() = String(bytes, Charset.forName("ISO-8859-1"))

    fun getBytes(from: Int, to: Int): ByteArray {
        return bytes.copyOfRange(from, to)
    }
}
package org.jesperancinha.string.paradigm.performance2

import java.io.UnsupportedEncodingException
import java.util.*

class StringWrapper(string: String) {
    var bytes: ByteArray

    init {
        try {
            bytes = string.toByteArray(charset("ISO-8859-1"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    override fun toString(): String {
        var to: String
        try {
            to = kotlin.String(bytes, "ISO-8859-1")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            to = null
        }
        return to
    }

    fun getBytes(from: Int, to: Int): ByteArray {
        return Arrays.copyOfRange(bytes, from, to)
    }
}
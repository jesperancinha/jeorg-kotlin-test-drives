package org.jesperancinha.ktd.util2.codec

import java.util.stream.Collectors

/**
 * Created by jofisaes on 10/06/2021
 */
class CodecShiftKey {

    companion object {

        val ALPHABET = "abcdefghijklmnopqrstuvwxyz"

        fun encode(string: String, shift: Int): String {
            return string.chars().mapToObj {
                val iChar = ALPHABET.indexOf(it.toChar())
                var newShift: Int = shift
                if (shift > ALPHABET.length) {
                    newShift -= (shift / ALPHABET.length) * shift
                }
                var newI = iChar + shift
                if (newI >= ALPHABET.length) {
                    newI -= ALPHABET.length
                }
                ALPHABET[newI]
            }.map { it.toString() }.collect(Collectors.joining(""))
        }

        fun decode(encoded: String, shift: Int): String {
            return encoded.chars().mapToObj {
                val iChar = ALPHABET.indexOf(it.toChar())
                var newShift: Int = shift
                if (shift > ALPHABET.length) {
                    newShift -= (shift / ALPHABET.length) * shift
                }
                var newI = iChar - shift
                if (newI < 0) {
                    newI += ALPHABET.length
                }
                ALPHABET[newI]
            }.map { it.toString() }.collect(Collectors.joining(""))        }
    }
}
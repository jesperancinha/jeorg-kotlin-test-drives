package org.jesperancinha.ktd.util3.codec

import java.util.stream.Collectors

/**
 * Created by jofisaes on 11/06/2021
 */
class CodecVigenere {

    companion object {

        private const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"

        fun encode(string: String, key: String): String {
            return string.chars().mapToObj { it }.collect(Collectors.toList())
                .mapIndexed { index: Int, c: Int ->
                    val newIndex = index - (index / key.length) * key.length
                    encodeIntChar(c, ALPHABET.indexOf(key[newIndex])).toString()
                }
                .stream().collect(Collectors.joining())

        }


        fun decode(string: String, key: String): String {
            return string.chars().mapToObj { it }.collect(Collectors.toList())
                .mapIndexed { index: Int, c: Int ->
                    val newIndex = index - (index / key.length) * key.length
                    decodeIntChar(c, ALPHABET.indexOf(key[newIndex])).toString()
                }
                .stream().collect(Collectors.joining())
        }

        private fun decodeIntChar(it: Int, shift: Int): Char {
            val iChar = ALPHABET.indexOf(it.toChar())
            var newShift: Int = shift
            if (shift > ALPHABET.length) {
                newShift -= (shift / ALPHABET.length) * shift
            }
            var newI = iChar - shift
            if (newI < 0) {
                newI += ALPHABET.length
            }
            return ALPHABET[newI]
        }

        private fun encodeIntChar(it: Int, shift: Int): Char {
            val iChar = ALPHABET.indexOf(it.toChar())
            var newShift: Int = shift
            if (shift > ALPHABET.length) {
                newShift -= (shift / ALPHABET.length) * shift
            }
            var newI = iChar + shift
            if (newI >= ALPHABET.length) {
                newI -= ALPHABET.length
            }
            return ALPHABET[newI]
        }
    }
}
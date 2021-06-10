package org.jesperancinha.ktd.util1.codec

import java.util.stream.Collectors

/**
 * Created by jofisaes on 10/06/2021
 */
class Codec16Ascii {

    companion object {
        fun encode(char: Char): Int {
            return (char.toInt()) * 16
        }

        fun decode(code: Int): Char {
            return (code / 16).toChar()
        }

        fun decode(encodedString: String): String {
            return String(encodedString.split(" ").stream().map { decode(it.toInt()) }.collect(Collectors.toList()).toCharArray())
        }

        fun encode(string: String): String {
           return string.chars().map { encode(it.toChar()) }.mapToObj { it.toString() }.collect(Collectors.joining(" "))
        }
    }

}
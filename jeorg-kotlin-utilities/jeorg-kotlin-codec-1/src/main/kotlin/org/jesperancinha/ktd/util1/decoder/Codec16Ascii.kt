package org.jesperancinha.ktd.util1.decoder

/**
 * Created by jofisaes on 10/06/2021
 */
class Codec16Ascii {

    companion object {
        fun encode(char: Char): Int {
            return (char.toInt())*16
        }

        fun decode(code: Int): Char {
            return (code/16).toChar()
        }
    }

}
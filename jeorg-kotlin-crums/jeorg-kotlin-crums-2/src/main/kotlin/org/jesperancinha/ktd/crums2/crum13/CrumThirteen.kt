package org.jesperancinha.ktd.crums2.crum13

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumThirteen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 13 -  Array type functions"))
                .reset()

            val volumesInInt = intArrayOf(1, 2, 3)
            val isbnInDouble = doubleArrayOf(9789461052872.0, 9789085067238.0, 9789461055712.0)
            val pagesinLong = longArrayOf(290, 289, 287)
            val yearInShort = shortArrayOf(2015, 2014, 2014)
            val lastTwoDigitsInByte = byteArrayOf(72, 38, 12)
            val isbnInFloat = floatArrayOf(9.789461052872E12F, 9.789085067238E12F, 9.789461055712E12F)
            val inPosesionInBoolean = booleanArrayOf(true, true, true)
            val titleInArray = arrayOf("De Sprong", "De Opmaat", "De Finale")

            ConsolerizerComposer.outSpace()
                .magenta("We will now read this data using the arrays we've created")
                .reset()
            (0..2)
                .forEach {
                    ConsolerizerComposer.outSpace().none()
                        .cyan("The book ${titleInArray[it]},")
                        .yellow("has ISBN ${isbnInDouble[it]} which can also be represented as ${isbnInFloat[it]}.")
                        .green("It was printed in ${yearInShort[it]}.")
                        .magenta("The last two digits are ${lastTwoDigitsInByte[it]}")
                        .blue("and I own it? ${inPosesionInBoolean[it]}.")
                        .orange("Oh yes, it has ${pagesinLong[it]} pages and it is volume ${volumesInInt[it]} of this collection.")
                        .newLine()
                        .reset()
                }
        }
    }
}

package org.jesperancinha.ktd.crums1.crum20

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class NumberFormattingAndRounding {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 20 - Number format and rounding up"))
                .none()
                .red("We can format a number like 5,6778").yellow("into").green("%.2f".format(5.6778)).newLine()
                .red("Another example with the same number is 5,6778").yellow("into").green("%.10f".format(5.6778))
                .newLine()
                .red("Yet another one is 5,6778").yellow("into").green("%.0f".format(5.6778)).newLine()
                .red("We can convert it into int 5,6778").yellow("into").green("%d", 5.6778.toInt()).newLine()
                .red("And also round it into int 5,6778").yellow("into").green("%d", 5.6778.roundToInt()).newLine()
                .magenta("Do you see the difference?").newLine()
                .red("Rounding can also be done with Long 5,6778").yellow("into").green("%d", 5.6778.roundToLong())
                .newLine()
                .reset()
        }
    }
}
package org.jesperancinha.ktd.crums1.crum16

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class NumbersInKotlin {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 16 - Numbers in Kotlin"))
                .none()
                .blue("Byte").red(Byte.MIN_VALUE).green(Byte.MAX_VALUE).orange(Byte.SIZE_BITS).orange(Byte.SIZE_BYTES)
                .newLine()
                .blue("Short").red(Short.MIN_VALUE).green(Short.MAX_VALUE).orange(Short.SIZE_BITS)
                .orange(Short.SIZE_BYTES).newLine()
                .blue("Int").red(Int.MIN_VALUE).green(Int.MAX_VALUE).orange(Int.SIZE_BITS).orange(Int.SIZE_BYTES)
                .newLine()
                .blue("Long").red(Long.MIN_VALUE).green(Long.MAX_VALUE).orange(Long.SIZE_BITS).orange(Long.SIZE_BYTES)
                .newLine()
                .blue("Float").red(Float.MIN_VALUE).green(Float.MAX_VALUE).orange(Float.SIZE_BITS)
                .orange(Float.SIZE_BYTES).magenta(Float.NEGATIVE_INFINITY).magenta(Float.POSITIVE_INFINITY)
                .magenta(Float.NaN).newLine()
                .blue("Double").red(Double.MIN_VALUE).green(Double.MAX_VALUE).orange(Double.SIZE_BITS)
                .orange(Double.SIZE_BYTES).magenta(Double.NEGATIVE_INFINITY).magenta(Double.POSITIVE_INFINITY)
                .magenta(Double.NaN).newLine()
                .reset()

            ConsolerizerComposer.outSpace()
                .magenta("If we print Int.MaxValue plus 2 we get %d", Int.MAX_VALUE + 2)
                .magenta("If we print Int.MaxValue times 2 we get %d", Int.MAX_VALUE * 5)
                .magenta("If we print Int.MaxValue plus 2 we get ${Int.MAX_VALUE + 2}")
                .magenta("If we print Int.MaxValue times 2 we get ${Int.MAX_VALUE * 5}")
                .reset()
        }
    }
}
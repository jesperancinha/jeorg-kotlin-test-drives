package org.jesperancinha.ktd.crums1.crum10

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class InfixFunctions {
    companion object {

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 10 - Infix Functions"))
                .green("a" eq "a")
                .green("b" eq "b")
                .green("c" eq "c")
                .green("d" eq "d")
                .reset()

            try {
                1 eq 2
            } catch (error: Error) {
                ConsolerizerComposer.outSpace()
                    .blue(error)
                    .reset()
            }
        }
    }
}

infix fun <T> T.eq(other: T): Boolean {
    if (this == other) return true
    else throw AssertionError()
}

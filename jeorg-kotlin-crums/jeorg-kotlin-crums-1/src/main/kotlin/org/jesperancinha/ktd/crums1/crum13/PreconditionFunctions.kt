package org.jesperancinha.ktd.crums1.crum13

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class PreconditionFunctions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace().cyan(ConsolerizerComposer.title("Crum 13 - Kotlin precondition functions"))
                .magenta("In kotlin there are 5 main precondition functions").none()
                .orange("checkNotNull", "require", "requireNotNull", "error", "assert").reset()

            ConsolerizerComposer.outSpace().green("checkNotNull")

            val lyric: String? = null

            try {
                checkNotNull(lyric)
            } catch (e: IllegalStateException) {
                ConsolerizerComposer.outSpace().magenta("lyric is checked not to be null").yellow(e).reset();
            }

            val goingToAGig: Boolean = false

            try {
                require(goingToAGig)
            } catch (e: IllegalArgumentException) {
                ConsolerizerComposer.outSpace().magenta("goingToAGig needs to be true, otherwise we stay at home")
                    .yellow(e).reset()
            }


            try {
                requireNotNull(lyric)
            } catch (e: IllegalArgumentException) {
                ConsolerizerComposer.outSpace().magenta("lyric is required not to be null")
                    .magenta("The check refers to a state.")
                    .magenta("The require refers to a value. If the value is null, then the state is ok, but argument is wrong")
                    .yellow(e).reset()
            }

            try {
                error("This is an error")
            } catch (e: IllegalStateException) {
                ConsolerizerComposer.outSpace()
                    .magenta("if we signal our own error, we are affecting the state of the application").yellow(e)
            }

            try {
                assert(lyric != null)
                ConsolerizerColor.RED.printThrowableAndExit(RuntimeException("Please make you run this program with the -ea VM option. Otherwise, the assertions will not work"))
            } catch (e: AssertionError) {
                ConsolerizerComposer.outSpace().magenta("in case of an assertion we throw a throwable error.").yellow(e)
            } catch (e: RuntimeException) {
                ConsolerizerComposer.outSpace()
                    .magenta("This is unexpected! Please run your program with -ea VM option").yellow(e)
            }


        }
    }
}
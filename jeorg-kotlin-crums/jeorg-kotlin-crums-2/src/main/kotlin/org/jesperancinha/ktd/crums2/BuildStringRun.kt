package org.jesperancinha.ktd.crums2

import org.jesperancinha.console.consolerizer.console.Consolerizer
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class BuildStringRun {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val result = buildString {
                ConsolerizerComposer.outSpace().bgGreen("I am of type ${this::class.simpleName}")
                append("Hello, ")
                append("world!")
                appendLine()
                append("This is Kotlin's buildString function.")
            }

            println(result)
        }
    }
}
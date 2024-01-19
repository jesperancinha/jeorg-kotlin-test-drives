package org.jesperancinha.ktd.crums1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class PrintToTheConsole {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 1 - Kotlin just started!"))
            println("I love to go to the beach")
        }
    }
}
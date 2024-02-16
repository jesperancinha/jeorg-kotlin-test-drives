package org.jesperancinha.ktd.crums3.consoleprint

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class ConsolerizerPrint {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 1 - A new beach season has started! 🏖"))
        }
    }
}
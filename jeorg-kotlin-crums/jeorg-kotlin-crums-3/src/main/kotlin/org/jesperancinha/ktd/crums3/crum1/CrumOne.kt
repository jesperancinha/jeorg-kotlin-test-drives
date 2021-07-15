package org.jesperancinha.ktd.crums3.crum1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class CrumOne {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 1 - A new beach season has started! 🏖"))
        }
    }
}
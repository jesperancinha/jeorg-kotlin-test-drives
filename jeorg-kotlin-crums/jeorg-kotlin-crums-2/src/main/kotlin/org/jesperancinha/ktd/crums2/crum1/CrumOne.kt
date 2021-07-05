package org.jesperancinha.ktd.crums2.crum1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class CrumOne {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 1 - Let's do this!"))
        }
    }
}
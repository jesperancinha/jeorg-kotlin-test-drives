package org.jesperancinha.ktd.crums1.crum9

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class InconstantVariable {
    companion object {
        private val magicDice: Int
            get() = (6 * Math.random()).toInt()

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 9 - Inconstant variable"))
                .green(magicDice)
                .green(magicDice)
                .green(magicDice)
                .green(magicDice)
                .reset()
        }
    }
}
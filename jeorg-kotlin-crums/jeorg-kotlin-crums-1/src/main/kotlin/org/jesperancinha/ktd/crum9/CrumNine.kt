package org.jesperancinha.ktd.crum9

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 15/06/2021
 */
class CrumNine {
    companion object {
        private val magicDice: Int
            get() = (6 * Math.random()).toInt()

        @JvmStatic
        fun main(args: Array<String>) {
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
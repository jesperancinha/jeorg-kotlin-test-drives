package org.jesperancinha.ktd.crums3.crum1

import arrow.optics.Lens
import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

@optics
data class Account(val balance: Int, val available: Int) {
    companion object
}

class CrumOne {
    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().red(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 1 - Optics in Arrow with code generation")
            logger.info("Hello World!")
            logger.info("Program arguments: ${args.joinToString()}")
            val account = Account(1000, 2000)
            val balanceLens: Lens<Account, Int> = Account.balance
            val availableLens: Lens<Account, Int> = Account.available

            println(balanceLens)
            println(balanceLens.get(account))
            println(availableLens.get(account))
        }
    }
}
 package org.jesperancinha.ktd.crums3.arrowsprogrammatically

import arrow.optics.Lens
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

data class Account(val balance: Int, val available: Int) {
    companion object
}

val accountLens: Lens<Account, Int> = Lens(
    get = { account -> account.balance },
    set = { player, value -> player.copy(balance = value, available = player.available + value) }
)

val account = Account(70, 60)

class ArrowsProgrammatically {

    companion object {
        private val logger = object {
            fun info(logText: String) = ConsolerizerComposer.out().red(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightBlue(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 5 - Programmatically made optics in Arrow")

            logger.info("Current account status: $account")
            val result = accountLens.set(account, 10)
            logger.info("Current account status: $result")
            val result2 = accountLens.set(result, -50)
            logger.info("Current account status: $result2")
            val result3 = accountLens.get(account)
            logger.info("Current account status: $result3")

        }
    }
}
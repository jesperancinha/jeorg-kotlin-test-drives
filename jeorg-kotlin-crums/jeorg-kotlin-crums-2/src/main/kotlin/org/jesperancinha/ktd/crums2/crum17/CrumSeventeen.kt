package org.jesperancinha.ktd.crums2.crum17

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 *
 * Note: Double is being used for illustration purposes
 * Double is not a good choice for needed precision and accuracies needed for financial transactions.
 */
class CrumSeventeen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 17 - private properties"))
                .reset()

            val money = Money(10.0)

            ConsolerizerComposer.outSpace()
                .green("Your money is")
                .yellow(money)
                .reset()

            Coin.values()
                .forEach {
                    money.coin = it
                    ConsolerizerComposer.outSpace().none()
                        .blue("The original value converted to").green(it).blue("is").green(money.convert)
                        .newLine()
                        .reset()
                }
        }
    }
}
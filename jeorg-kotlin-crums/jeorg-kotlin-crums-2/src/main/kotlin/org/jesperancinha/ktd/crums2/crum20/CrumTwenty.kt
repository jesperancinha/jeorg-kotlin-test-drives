package org.jesperancinha.ktd.crums2.crum20

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class CrumTwenty {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 20 - Inheritance and polymorphism"))
                .reset()

            val brownie = Brownie()
            val caramelShortBread = CaramelShortBread()
            val bigBrownie = BigBrownie()
            ConsolerizerComposer.outSpace()
                .magenta("The following are all cakes")
                .blue("A brownie is ${brownie.symbol()}")
                .blue("A caramel shortbread is ${caramelShortBread.symbol()}")
                .blue("A big brownie is ${bigBrownie.symbol()}")
                .green("Be noted that the symbols do not match what we know about these cakes 😁")
                .reset()

            ConsolerizerComposer.outSpace()
                .magenta("The result is ${bigBrownie.eatCake()} and the price is ${bigBrownie.price}")
                .magenta("The result is ${brownie.eatCake()} and the price is ${brownie.getPrice()}")
                .reset()
        }
    }
}
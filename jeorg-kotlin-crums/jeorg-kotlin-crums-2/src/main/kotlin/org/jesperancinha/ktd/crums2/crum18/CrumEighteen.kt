package org.jesperancinha.ktd.crums2.crum18

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumEighteen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 18 - Creating and initializing"))
                .reset()

            val lamp1 = Lamp("StarWars")

            ConsolerizerComposer.outSpace()
                .magenta("We will not be able to print this lamp because the main color has not been initialized")
                .reset()
            try {
                println(lamp1)
            } catch (ex: UninitializedPropertyAccessException) {
                ConsolerizerComposer.outSpace()
                    .orange(ex)
            }

            lamp1.setupColor("Gray")

            ConsolerizerComposer.outSpace()
                .magenta("Now that it is initialized, we can finally print it")
                .orange(lamp1)
                .reset()

            ConsolerizerComposer.outSpace()
                .magenta("Notice that the lazy variable height has just been initialized")
                .reset()

            val lamp2 = Lamp(
                theme = "StarTrek",
                height = 200
            )

            lamp2.setupColor("Blue Blue")

            ConsolerizerComposer.outSpace()
                .magenta("Using named arguments we just created another lamp")
                .yellow(lamp2)
                .reset()

            ConsolerizerComposer.outSpace()
                .blue("May the 4th be with you")
                .blue("Live long and prosper")
                .reset()
        }
    }
}
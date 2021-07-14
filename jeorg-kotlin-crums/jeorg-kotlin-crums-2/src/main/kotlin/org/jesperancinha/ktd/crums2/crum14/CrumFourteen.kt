package org.jesperancinha.ktd.crums2.crum14

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumFourteen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 14 - Maps in Kotlin"))
                .reset()

            ConsolerizerComposer.outSpace()
                .green("We make a make map of Taschen books about painters and how many pages they have")
                .reset()

            val pages = mapOf(
                "Matisse" to 256,
                "Dalí" to 224,
                "Hopper" to 200,
                "Van Gogh" to 256,
                "Kandinsly" to 200
            )
            ConsolerizerComposer.outSpace()
                .orange("We now created this map")
                .yellow(pages)
                .reset()

            pages.forEach { (key, value) ->
                ConsolerizerComposer.outSpace().none()
                    .magenta("The book").blue(key).magenta("has").blue(value).magenta("pages")
                    .newLine()
                    .reset()
            }

            ConsolerizerComposer.outSpace()
                .green("We can also create a map using Pair")
                .reset()

            val pagesPair = mapOf(
                Pair("Matisse", 256),
                Pair("Dalí", 224),
                Pair("Hopper", 200),
                Pair("Van Gogh", 256),
                Pair("Kandinsly", 200)
            )

            ConsolerizerComposer.outSpace()
                .orange("We now created this map")
                .yellow(pagesPair)
                .reset()


            ConsolerizerComposer.outSpace()
                .green("If we try to add an existing key")
                .reset()

            pagesPair as MutableMap += "Matisse" to 1234

            ConsolerizerComposer.outSpace()
                .orange("We now created this map")
                .yellow(pagesPair)
                .reset()

        }
    }
}
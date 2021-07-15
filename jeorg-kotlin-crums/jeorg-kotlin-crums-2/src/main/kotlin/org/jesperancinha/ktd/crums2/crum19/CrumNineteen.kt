package org.jesperancinha.ktd.crums2.crum19

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class CrumNineteen {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 19 - Parameter Initialization Order"))
                .reset()

            val badTomatoSoup = BadSoup("Tomato", "Parsley")
            val tomatoSoup = Soup("Tomato", "Parsley")

            val ingredientResult = badTomatoSoup.ingredientResult
            ConsolerizerComposer.out()
                .magenta("We first make a bad soup where most of the information is missing")
                .green(ingredientResult)
                .reset()

            val ingredientResult1 = tomatoSoup.ingredientResult

            ConsolerizerComposer.out()
                .magenta("Then we make a good soup with all the main ingredients")
                .green(ingredientResult1)
                .reset()

        }
    }
}
package org.jesperancinha.ktd.crums2.crum3

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 07/07/2021
 */
class CrumThree {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 3 - The magic of lists in Kotlin"))
                .reset()

            exploreImmutable()
            exploreMutable()
            exploreForEach()
            exploreDestructuring()
        }

        private fun exploreDestructuring() {
            val vitamins = listOf("A", "B", "C", "B12", "K")
            val (vitamin1, vitamin2, _, _, vitamin3) = vitamins

            ConsolerizerComposer.out().magenta("Destructuring")
                .green(vitamin1)
                .green(vitamin2)
                .green(vitamin3)
                .reset()
        }

        private fun exploreForEach() {
            ConsolerizerComposer.out().magenta("For each").reset()
            val vitamins = listOf("A", "B", "C", "B12", "K")
            vitamins.forEach {
                ConsolerizerComposer.out().none()
                    .random(it)
            }
            ConsolerizerComposer.out().newLine().reset()
            for (vitamin in vitamins) {
                ConsolerizerComposer.out().none()
                    .random(vitamin)
            }
            ConsolerizerComposer.out().newLine().reset()
            vitamins.forEachIndexed { index, vitamin ->
                ConsolerizerComposer.out().none()
                    .random(index).random("-").random(vitamin).newLine().reset()
            }
        }

        private fun exploreMutable() {
            val vitamins = listOf("A", "B")
            val mutableVitamins = vitamins.toMutableList()
            mutableVitamins.add("C")
            mutableVitamins += "D"
            mutableVitamins.remove("A")
            mutableVitamins.add(1, "B12")
            mutableVitamins[0] = "K"
            ConsolerizerComposer.outSpace()
                .magenta("Mutable")
                .green("Mutable Lists")
                .yellow(mutableVitamins)
                .yellow(mutableVitamins[0])
                .yellow(mutableVitamins.getOrElse(4) { "Magnesium" })
                .yellow(mutableVitamins.getOrNull(4))
                .yellow(mutableVitamins.contains("B"))
                .yellow(mutableVitamins.contains("C"))
                .yellow(mutableVitamins.containsAll(listOf("A", "B")))
                .yellow(mutableVitamins.containsAll(listOf("B", "A")))
                .reset()
            mutableVitamins -= "D"
            ConsolerizerComposer.outSpace()
                .green("Mutable Lists")
                .yellow(mutableVitamins)
                .reset()
            mutableVitamins.removeIf { it.startsWith("B") }
            ConsolerizerComposer.outSpace()
                .green("Mutable Lists")
                .yellow(mutableVitamins)
                .reset()
            mutableVitamins.clear()
            ConsolerizerComposer.outSpace()
                .green("Mutable Lists")
                .yellow(mutableVitamins)
                .reset()
        }

        private fun exploreImmutable() {
            val vitamins = listOf("A", "B")
            ConsolerizerComposer.outSpace()
                .magenta("Immutable")
                .green("Immutable Lists")
                .yellow(vitamins)
                .yellow(vitamins[0])
                .yellow(vitamins.getOrElse(4) { "Magnesium" })
                .yellow(vitamins.getOrNull(4))
                .yellow(vitamins.contains("B"))
                .yellow(vitamins.contains("C"))
                .yellow(vitamins.containsAll(listOf("A", "B")))
                .yellow(vitamins.containsAll(listOf("B", "A")))
                .reset()
        }
    }
}
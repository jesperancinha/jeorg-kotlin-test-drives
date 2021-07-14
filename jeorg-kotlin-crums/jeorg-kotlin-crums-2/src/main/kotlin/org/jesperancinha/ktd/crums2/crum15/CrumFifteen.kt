package org.jesperancinha.ktd.crums2.crum15

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumFifteen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 15 - Map functions"))
                .reset()
            val filmCollection = mapOf(
                "Arachnophobia" to "DVD",
                "Forrest Gump" to "VHS",
                "Pulp Fiction" to "VHS",
                "The Godfather" to "Blu-ray",
                "Psycho" to "Blu-ray"
            )

            ConsolerizerComposer.outSpace()
                .yellow("We have this collection")
                .yellow(filmCollection)
                .reset()
            ConsolerizerComposer.outSpace()
                .green("We can get values from the map")
                .green(filmCollection["Arachnophobia"])
                .green(filmCollection["Gone with the wind"])
                .green(filmCollection.getValue("Psycho"))
                .green(filmCollection.getOrElse("The Untouchables") {
                    filmCollection as MutableMap += "The Untouchables" to "MPEG"
                    "MPEG"
                })
                .green(filmCollection.getOrDefault("Indiana Jones and the Temple of Doom", "Scrap Paper"))
                .reset()

            ConsolerizerComposer.outSpace()
                .yellow("Our map has now a new element!")
                .yellow(filmCollection)
                .reset()

            (filmCollection as MutableMap)["Arachnophobia"] = "Blu-ray"
            filmCollection.put("The Mauritanian", "Blu-ray")

            ConsolerizerComposer.outSpace()
                .yellow("Our map has now another element!")
                .yellow(filmCollection)
                .reset()

            filmCollection.putAll(listOf("Die Hard" to "Betamax", "Die Hard II" to "VHS", "Die Hard III" to "DVD"))

            ConsolerizerComposer.outSpace()
                .yellow("Our map has now three other elements!")
                .yellow(filmCollection)
                .reset()


            val arachnophobiaType = filmCollection.getOrPut("Arachnophobia") { "Betamax" }
            val bateriesNotIncluded = filmCollection.getOrPut("Batteries Not Included") { "VHS" }

            ConsolerizerComposer.outSpace()
                .yellow("Our map has now one more element!")
                .orange("The new element has type")
                .magenta(bateriesNotIncluded)
                .orange("An old element is still of type")
                .magenta(arachnophobiaType)
                .yellow(filmCollection)
                .reset()

            filmCollection.remove("Arachnophobia")

            ConsolerizerComposer.outSpace()
                .yellow("Our map has now one less element!")
                .yellow(filmCollection)
                .reset()


            ConsolerizerComposer.outSpace()
                .yellow("If we could take one element out")
                .yellow(filmCollection - "Forrest Gump")
                .reset()

            filmCollection -= "Forrest Gump"

            ConsolerizerComposer.outSpace()
                .yellow("Our map has now one less element!")
                .yellow(filmCollection)
                .reset()

            filmCollection.clear()

            ConsolerizerComposer.outSpace()
                .yellow("Our elements are gone!")
                .yellow(filmCollection)
                .rainbow(100)
                .reset()

        }
    }
}
package org.jesperancinha.ktd.crums2.crum12

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumTwelve {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 12 - The apparent immutable List"))
                .reset()

            val novels = listOf("Brida",
                "O Alquimista",
                "O Demónio e a Senhora Prym",
                "Brida",
                "O Alquimista",
                "O Demónio e a Senhora Prym")

            ConsolerizerComposer.outSpace()
                .orange(novels)
                .reset()

            val toSet = novels.toSet()

            ConsolerizerComposer.outSpace()
                .orange(toSet)
                .reset()

            val toList = toSet.toList()

            ConsolerizerComposer.outSpace()
                .orange(toList)
                .reset()

            val toListAsMutable = toList as MutableList

            ConsolerizerComposer.outSpace()
                .magenta("We can change an immutable list if we cast and use the MutableList interface")
                .yellow("The following three records are going to be added to the immutable list")

            toListAsMutable.add("MAUS")
            toList += "Het Achterhuis"
            toListAsMutable += "Pippa zoekt een Pappa"

            ConsolerizerComposer.outSpace()
                .orange(toList)
                .reset()

        }
    }
}
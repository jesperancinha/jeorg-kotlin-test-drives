package org.jesperancinha.ktd.crums2.crum10

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 14/07/2021
 */
class CrumTen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 10 - Creating sets in kotlin"))
                .reset()
            val harryPotterVolumes = setOf("Steen van Wijzen",
                "Geheime Kamer",
                "Gevangene van Azkaban",
                "Vuurbeker",
                "Orde van Feniks",
                "Halfbloed Prins",
                "Relieken van de Dood")

            ConsolerizerComposer.outSpace()
                .yellow("We have these in a set")
                .red("⬅️ 🪄 $harryPotterVolumes 🪄 ➡️")
                .reset()

            ConsolerizerComposer.outSpace()
                .blue("If we create a set with repeated elements")
                .reset()

            val harryPotterVolumesExtendedTry = setOf("Steen van Wijzen",
                "Geheime Kamer",
                "Gevangene van Azkaban",
                "Vuurbeker",
                "Orde van Feniks",
                "Halfbloed Prins",
                "Relieken van de Dood",
                "Steen van Wijzen",
                "Geheime Kamer",
                "Gevangene van Azkaban",
                "Vuurbeker",
                "Orde van Feniks",
                "Halfbloed Prins",
                "Relieken van de Dood")

            ConsolerizerComposer.outSpace()
                .yellow("We have these in a set")
                .red("⬅️ 🪄 $harryPotterVolumesExtendedTry 🪄 ➡️")
                .reset()

            ConsolerizerComposer.outSpace()
                .yellow("All elements of the second collection, contain all elements of the first = ${
                    harryPotterVolumesExtendedTry.containsAll(harryPotterVolumes)
                }")
                .reset()
            ConsolerizerComposer.outSpace()
                .green("Now we check per element")
                .reset()
            harryPotterVolumesExtendedTry
                .forEach {
                    ConsolerizerComposer.outSpace().none()
                        .magenta(it)
                        .yellow("is in")
                        .blue(harryPotterVolumesExtendedTry.contains(it))
                        .newLine()
                        .reset()
                }

            ConsolerizerComposer.outSpace().none()
                .magenta("Mordor")
                .yellow("is in")
                .blue(harryPotterVolumesExtendedTry.contains("Mordor"))
                .newLine()
                .reset()

            ConsolerizerComposer.outSpace()
                .orange("We can access elements in a set per index. However the bigger the index, the longer it will take to get an element. Under the hood, the set is being iterated until the index provided")
                .reset()

            (harryPotterVolumes.indices)
                .forEach {
                    ConsolerizerComposer.outSpace()
                        .cyan(harryPotterVolumes.elementAt(it))
                        .reset()
                }

            ConsolerizerComposer.outSpace()
                .green("We can access elements and their index with forIndexed")
                .reset()

            harryPotterVolumes.forEachIndexed { index, value ->
                ConsolerizerComposer.outSpace().none()
                    .magenta(index)
                    .yellow("is ")
                    .blue(value)
                    .newLine()
                    .reset()
            }

            ConsolerizerComposer.outSpace()
                .green("We now create a huje list of volumes, convert it to a set to get unique volume names. We could get all of them, but not always.")
                .reset()

            val generatedList = (1..1000)
                .map { harryPotterVolumesExtendedTry.random() }

            ConsolerizerComposer.outSpace()
                .magenta("We now have a list of ${generatedList.size} elements")
                .magenta("We convert it now to a set")
                .reset()

            val generatedSet = generatedList.toSet()
            ConsolerizerComposer.outSpace()
                .magenta("We now have a list of ${generatedSet.size} elements")
                .magenta("We now have these elements")
                .green(generatedSet)
                .reset()

            ConsolerizerComposer.outSpace()
                .orange("The same set can be generated this with a mutableSSet")
                .reset()

            var mutableSetOfVolumes = mutableSetOf<String>()
            (1..1000)
                .forEach {
                    mutableSetOfVolumes += harryPotterVolumesExtendedTry.random()
                }


            ConsolerizerComposer.outSpace()
                .magenta("We now have a list of ${mutableSetOfVolumes.size} elements")
                .magenta("We now have these elements")
                .green(mutableSetOfVolumes)
                .reset()
        }
    }
}
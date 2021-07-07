package org.jesperancinha.ktd.crums2.crum2

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 07/07/2021
 */
class CrumTwo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 2 -Standard functions - apply, let, run, with, also, takeIf, takeUnless"))
                .reset()

            exploringApply()
            exploreLet()
            exploreRun()
            exploreWith()
            exploreAlso()
            exploreTakeIf()
            exploreTakeUnless()

            ConsolerizerComposer.outSpace()
                .black()
                .green("apply -> this -> receiver")
                .green("run -> this -> result")
                .green("with -> this -> result")
                .green("let -> it -> result")
                .green("also -> it -> receiver")
                .green("takeIf -> it -> null or receiver")
                .green("takeUnless -> it -> null or receiver")
        }

        private fun exploreTakeUnless() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("takeUnless")
                .reset()
            val building1 = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).takeUnless { it.bricks == 321L }?.toString()
            val building2 = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).takeUnless { it.bricks == 123L }?.toString()
            ConsolerizerComposer.outSpace()
                .red("We made this building: $building1")
                .red("But not this one: $building2")
                .reset()
        }

        private fun exploreTakeIf() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("takeIf")
                .reset()
            val building1 = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).takeIf { it.bricks == 321L }?.toString()
            val building2 = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).takeIf { it.bricks == 123L }?.toString()
            ConsolerizerComposer.outSpace()
                .red("We made this building: $building2")
                .red("But not this one: $building1")
                .reset()
        }

        private fun exploreAlso() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("also")
                .reset()

            val building = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).also {
                outside()
                it.buildIt()
                println("It was made using ${it.concreteWeight()}")
                println("It was made using ${it.steelWeight()}")
                println("It was made using ${it.brickStats()}")
                it.printReport()
                println("We do not return anything from apply. It returns the receiver implicitly")
            }
            ConsolerizerComposer.outSpace()
                .red("We made this building: $building")
        }

        private fun exploreWith() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("with")
                .reset()

            val building = with(
                Building(
                    bricks = 123,
                    goal = "Super duper Shopping mall",
                    concreteKg = 10000.0,
                    steelKg = 30000.0
                )
            ) {
                outside()
                buildIt()
                println("It was made using ${concreteWeight()}")
                println("It was made using ${steelWeight()}")
                println("It was made using ${brickStats()}")
                printReport()
                println("We now return `this` value this back from with")
                this
            }
            ConsolerizerComposer.outSpace()
                .red("We made this building: $building")
        }

        private fun exploreRun() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("run")
                .reset()

            val building = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).run {
                outside()
                buildIt()
                println("It was made using ${concreteWeight()}")
                println("It was made using ${steelWeight()}")
                println("It was made using ${brickStats()}")
                printReport()
                println("We now return `this` value this back from run")
                this
            }
            ConsolerizerComposer.outSpace()
                .red("We made this building: $building")
        }

        private fun exploreLet() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("let")
                .reset()

            val building = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).let {
                outside()
                it.buildIt()
                println("It was made using ${it.concreteWeight()}")
                println("It was made using ${it.steelWeight()}")
                println("It was made using ${it.brickStats()}")
                it.printReport()
                println("We now return this value $it back from let")
                it
            }
            ConsolerizerComposer.outSpace()
                .red("We made this building: $building")
        }

        private fun outside() {
            print("See? We can reach the outside!")
        }

        private fun exploringApply() {
            ConsolerizerComposer.outSpace()
                .black()
                .bgRed("apply")
                .reset()

            val building = Building(
                bricks = 123,
                goal = "Super duper Shopping mall",
                concreteKg = 10000.0,
                steelKg = 30000.0
            ).apply {
                outside()
                buildIt()
                println("It was made using ${concreteWeight()}")
                println("It was made using ${steelWeight()}")
                println("It was made using ${brickStats()}")
                printReport()
                println("We do not return anything from apply. It returns the receiver implicitly")
            }

            ConsolerizerComposer.outSpace()
                .red("We made this building: $building")
        }
    }
}
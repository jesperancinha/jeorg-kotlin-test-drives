package org.jesperancinha.ktd.crums3.crum2

import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

enum class Side {
    DARK_SIDE, GOOD_SIDE
}

@optics
data class Character(val name: String, val side: Side) {
    companion object
}

@optics
data class Ship(val passengers: List<Character>) {
    companion object
}


class CrumTwo {
    companion object {

        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().orange(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightMagenta(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 2 - Lens revisited")

            val passengerLens = Ship.passengers
            val ship: Ship = Ship(listOf(Character("Luke Skywalker", Side.GOOD_SIDE)))
            val changedShip =passengerLens.set(ship, listOf(Character("Darh Vader", Side.DARK_SIDE)))

            logger.info("Before the invasion, the ship was configured like $ship")
            logger.info("After the invasion, the ship was configured like $changedShip")
        }
    }
}

package org.jesperancinha.ktd.crums3.crums3

import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

@optics
sealed class House {
    companion object{}
    data class Hut(val straws: Int) : House()
    data class Villa(val bricks: Int) : House()
    data class Apartment(val bricks: Int) : House()
}

class CrumThree {

    companion object{
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().orange(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightMagenta(ConsolerizerComposer.title(logText))
        }
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 3 - Prisms with code generation")

            val hutPrism = House.hut
            val villaPrism = House.villa
            val apartment = House.apartment
        }
    }
}
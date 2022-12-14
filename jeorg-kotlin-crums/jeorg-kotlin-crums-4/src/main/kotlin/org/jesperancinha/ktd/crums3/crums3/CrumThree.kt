package org.jesperancinha.ktd.crums3.crums3

import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

@optics
sealed class House {
    companion object {}
    data class Hut(val straws: Int) : House()
    data class Villa(val bricks: Int) : House()
    data class Apartment(val bricks: Int) : House()
}

class CrumThree {

    companion object {
        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().orange(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightMagenta(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 3 - Prisms with code generation")

            val hutPrism = House.hut
            val villaPrism = House.villa
            val apartment = House.apartment

            val hut1 = House.Hut(1000)
            val result1 = hutPrism.modify(hut1) {
                House.Hut(it.straws * 9)
            }
            val result12 = hutPrism.lift {
                House.Hut(it.straws * 8)
            }
            logger.info(hut1)
            logger.info(result1)
            logger.info(result12(hut1))
            val orNull = hutPrism.getOrNull(House.Hut(500))
            val orNull2 = hutPrism.getOrNull(House.Villa(500))
            val orNull3 = hutPrism.getOrNull(House.Apartment(500))
            logger.info(orNull)
            logger.info(orNull2)
            logger.info(orNull3)
        }

    }
}
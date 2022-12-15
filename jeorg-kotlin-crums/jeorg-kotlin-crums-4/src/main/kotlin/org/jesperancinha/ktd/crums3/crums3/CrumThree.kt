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
            fun info2(logText: Any?) = ConsolerizerComposer.out().red(logText)
            fun comment(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightMagenta(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 3 - Prisms with code generation")

            val hutPrism = House.hut
            val villaPrism = House.villa
            val apartmentPrim = House.apartment

            val hut1 = House.Hut(1000)
            val villa1 = House.Villa(2000)
            val apartment1 = House.Apartment(3000)

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

            val reverseGet = hutPrism.reverseGet(House.Hut(111))
            val reverseGet2 = villaPrism.reverseGet(House.Villa(111))
            val reverseGet3 = apartmentPrim.reverseGet(House.Apartment(111))

            logger.info(reverseGet)
            logger.info(reverseGet2)
            logger.info(reverseGet3)

            val liftHut = hutPrism.lift { it } (hut1)
            val liftVilla = villaPrism.lift { it } (villa1)
            val liftApartment = apartmentPrim.lift { it } (apartment1)

            logger.info2(liftHut)
            logger.info2(liftVilla)
            logger.info2(liftApartment)

            logger.comment("Compose works as an and and so everything is null")
            val composeGroundHouses = hutPrism compose villaPrism
            val orNullComposed1 = composeGroundHouses.getOrNull(hut1)
            val orNullComposed2 = composeGroundHouses.getOrNull(villa1)
            val orNullComposed3 = composeGroundHouses.getOrNull(apartment1)
            logger.info(orNullComposed1)
            logger.info(orNullComposed2)
            logger.info(orNullComposed3)


        }

    }
}
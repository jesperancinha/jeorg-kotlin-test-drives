package org.jesperancinha.ktd.crums3.crum13

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.random.Random


abstract class Ship {
    override fun toString(): String = "I'm a ship!"

    open fun addFuelGallons(liters: Long) = run {
        logger.info("Adding {} litter fuel to ship {} with plate {}", liters, this::class.java, this.hashCode())
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Ship::class.java)
    }
}

class BattleShip : Ship() {
    override fun toString(): String = "I'm a battleship!"
}

class SpaceShip : Ship() {
    override fun toString(): String = "I'm a spaceship!"
}

/**
 * Output in short means that T can only be used as a return type
 */
class ShipWarehouse<out T : Ship>(
    val ships: List<T>
) {
    fun readShip(index: Int) = ships[index]
}

/**
 * Input in short means that T can only be used as an input type
 */
class ShipFuelStation<in T : Ship> {
    fun fuel(ship: T) {
        ship.addFuelGallons(Random(100).nextLong())
    }
}

class InAndOuts {
    companion object {
        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
            fun infoText(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 13 - In and Out")

            val shipWarehouse = ShipWarehouse(listOf(SpaceShip(), BattleShip()))

            val fuelStation = ShipFuelStation<Ship>()
            logger.info("Fueling both ships...")
            shipWarehouse.ships.forEach {
                fuelStation.fuel(it)
            }
            logger.info("Fueling battle ships...")
            val fuelStation1 = ShipFuelStation<BattleShip>()
            shipWarehouse.ships.forEach {
                try {
                    fuelStation1.fuel(it as BattleShip)
                } catch (ex: ClassCastException) {
                    logger.info("This ship failed to fuel because it is not a battleship! it is a ${it.javaClass.simpleName} instead!")
                }
            }
            logger.info(shipWarehouse.readShip(0))
            logger.info(shipWarehouse.readShip(1))

            logger.info("This warehouse only contains battleships")
            val battleShipWarehouse: ShipWarehouse<BattleShip> = ShipWarehouse(listOf(BattleShip(), BattleShip()))
            val battleShipFuelStation = ShipFuelStation<BattleShip>()
            battleShipWarehouse.ships.forEach {
                battleShipFuelStation.fuel(it)
            }
            logger.info(battleShipWarehouse.readShip(0))
            logger.info(battleShipWarehouse.readShip(1))
            logger.infoText("Kotlin has lots of principles about restrictions. in and out are mainly about restricting T to be used as an input parameter only or as an output parameter only.")
        }
    }

}



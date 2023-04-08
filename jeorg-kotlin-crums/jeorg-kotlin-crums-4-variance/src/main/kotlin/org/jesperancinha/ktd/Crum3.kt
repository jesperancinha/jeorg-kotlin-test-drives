package org.jesperancinha.ktd

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface ShellFood {
    fun exclusive()
}

open class Clams : ShellFood {
    override fun toString(): String = "Clams"

    override fun exclusive() = logger.info("These clams are normal!")

    companion object {
        val logger:Logger= LoggerFactory.getLogger(Clams::class.java)
    }
}

class Mussels : ShellFood {

    override fun toString(): String = "Mussels"

    override fun exclusive() = logger.info("These mussels are big!")

    companion object {
        val logger:Logger= LoggerFactory.getLogger(Mussels::class.java)
    }
}

class ClamsRoyal : Clams() {
    override fun toString(): String = "Clams Royal"

    override fun exclusive() = logger.info("These clams are big!")

    companion object {
        val logger:Logger= LoggerFactory.getLogger(ClamsRoyal::class.java)
    }
}

class Plate<out T : ShellFood>(
    private val food: T,
) {
    fun servePlate() = logger.info("Plate of $food has been served!")

    override fun toString() = "Eating a plat of $food"

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Plate::class.java)
    }
}

class ContraPlate<in T :ShellFood> {
    fun servePlate(food: T) = logger.info("Plate of $food has been served!")
        .also { food.exclusive() }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ContraPlate::class.java)
    }
}

class ImmutableEatingOut {
    companion object {
        @JvmStatic
        fun main(args: Array<String> =  emptyArray()) {
            val plate = findPerfectPlate(0)
            println(plate)
            plate.servePlate()
            val plate2 = findPerfectPlate(1)
            println(plate2)
            plate2.servePlate()

            val contraPlate1 = findPerfectContraPlate(0)
            contraPlate1.servePlate(ClamsRoyal())
            (contraPlate1 as ContraPlate<Clams>).servePlate(Clams())
            (contraPlate1 as ContraPlate<ShellFood>).servePlate(Mussels())
            val contraPlate1A: ContraPlate<Clams> = contraPlate1
            contraPlate1A.servePlate(Clams())

            val contraPlate2 = findPerfectContraPlate(1)
            contraPlate2.servePlate(ClamsRoyal())
            (contraPlate2 as ContraPlate<Clams>).servePlate(Clams())

        }

        private fun findPerfectContraPlate(index: Int): ContraPlate<ClamsRoyal> = when (index) {
            0 -> ContraPlate<Clams>()
            1 -> ContraPlate()
            else -> throw RuntimeException()
        }

        private fun findPerfectPlate(index: Int): Plate<ShellFood> = when (index) {
            0 -> Plate(Clams())
            1 -> Plate(Mussels())
            else -> throw RuntimeException()
        }
    }
}
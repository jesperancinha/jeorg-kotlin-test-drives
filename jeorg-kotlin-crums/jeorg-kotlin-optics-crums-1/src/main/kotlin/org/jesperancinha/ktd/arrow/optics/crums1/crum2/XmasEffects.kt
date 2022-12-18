package org.jesperancinha.ktd.arrow.optics.crums1.crum2

import arrow.core.left
import arrow.optics.optics
import org.jesperancinha.ktd.arrow.optics.crums1.crum2.Color.*
import org.jesperancinha.ktd.arrow.optics.crums1.crum2.PendulumType.MIDDLE_AGE

internal interface ICost {
    fun isExpensive(): Boolean = false
}

enum class Color(val hashColorValue: String) : ICost {
    WHITE("FFFFFF"),
    GREEN("008000"),
    YELLOW("FFFF00"),
    FUCSHSIA("FF00FF"),
    RED("FF0000"),
    SILVER("C0C0C0"),
    GOLD("FFD700"),
    SILVER_METAL("C0C0C0") {
        override fun isExpensive(): Boolean = true
    },
    GOLD_METAL("FFD700") {
        override fun isExpensive(): Boolean = true
    }
}

enum class PendulumType() {
    MIDDLE_AGE,
    MODERN,
    ULTRA_MODERN,
    MODEL_3000
}

data class Pendulum(
    val lengthCm: Int = 2,
    val color: Color = GOLD,
    val type: PendulumType = MIDDLE_AGE
)

@optics
sealed class Ball(
    val radiusCm: Int = 4,
    val color: Color = GOLD,
    val pendulum: Pendulum = Pendulum()
) {
    @optics
    sealed class BigBall : Ball(radiusCm = 8) {
        companion object
    }

    @optics
    sealed class SilverBall : Ball(color = SILVER){
        companion object
    }

    @optics
    sealed class ExpensiveSilverBall : Ball(color = SILVER_METAL){
        companion object
    }

    companion object
}

@optics
sealed class Garland(
    val lengthCm: Int = 10,
    val colors: List<Color> = listOf(GREEN),
    val radiusCm: Int = 2
) {

    @optics
    sealed class XmasEvolutionGarland : Garland() {
        companion object
    }

    @optics
    sealed class CarnavalGarland :
        Garland(lengthCm = 20, colors = listOf(GOLD, FUCSHSIA, YELLOW, RED, SILVER), radiusCm = 5) {
        companion object
    }

    companion object
}

@optics
sealed class XmasTree(
    val balls: List<Ball> = emptyList(),
    val garlands: List<Garland> = emptyList()
) {
    companion object
}

/**
 * From top to bottom, a tree has 1 extra ball
 * Because it's a 3D model, each level will have the double of balls of the previous one
 * Single exception is the difference between the first and the second level where the first level always takes just one ball and the second 4
 * If:
 * 1. one floor does not have enough balls, then the tree gets unbalanced and we through InvalidTreeException
 * 2. No tree should be made of expensive materials at this stage. An attempt to do so should throw ForbiddenMaterialsException
 */
fun createTree(balls: List<Ball>, garlands: List<Garland>) {
    balls.first { ball: Ball -> ball.color.isExpensive() }.takeIf { it.color.isExpensive() }
        ?: throw ForbiddenMaterialsException()
    garlands.flatMap { it.colors }.first { color: Color -> color.isExpensive() }.takeIf { it.isExpensive() }
        ?: throw ForbiddenMaterialsException()
    val levels = when {
        balls.size == 1 -> 1
        (balls.size - 1) % 4 == 0 -> (balls.size - 1) / 4 + 1
        else -> throw InvalidTreeException()
    }



}

class ForbiddenMaterialsException : Exception()
class InvalidTreeException : Exception()

class XmasEffects {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {

        }
    }
}
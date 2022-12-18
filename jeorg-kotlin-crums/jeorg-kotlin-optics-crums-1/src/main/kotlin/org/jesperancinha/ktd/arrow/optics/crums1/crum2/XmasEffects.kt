package org.jesperancinha.ktd.arrow.optics.crums1.crum2

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

data class Ball(
    val radiusCm: Int = 4,
    val color: Color = GOLD,
    val pendulum: Pendulum = Pendulum()
)

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

class XmasEffects {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {

        }
    }
}
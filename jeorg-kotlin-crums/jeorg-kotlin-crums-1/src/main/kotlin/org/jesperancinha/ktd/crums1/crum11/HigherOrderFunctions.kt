package org.jesperancinha.ktd.crums1.crum11

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import kotlin.math.PI


/**
 * Higher order functions
 *
 * They use functions as parameters and/or functions as return value
 * @constructor Create empty Higher order functions
 */
class HigherOrderFunctions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 11 - Higher order functions"))
                .green(sandInArea()(1, 2, 5))
                .reset()
        }

        fun perCircleArea(tArea: Double, r: Int): Double {
            return tArea / (PI * r * r);
        }

        fun triangleArea(base: Int, height: Int, r: Int): Double {
            return perCircleArea((base * height / 2).toDouble(), r)
        }

        fun sandInArea(): ((Int, Int, Int) -> Double) {
            return ::triangleArea
        }

    }

}


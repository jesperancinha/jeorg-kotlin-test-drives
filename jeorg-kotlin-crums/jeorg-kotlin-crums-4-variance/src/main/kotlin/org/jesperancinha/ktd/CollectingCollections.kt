package org.jesperancinha.ktd

import java.util.Collections.sort

class CollectingCollections {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val squareZeros = listOf(SquareFromZeroPoint(4, 2), SquareFromZeroPoint(2, 2))
            sort(squareZeros) { leftSquare, rightSquare ->
                leftSquare.area().compareTo(rightSquare.area())
            }
            println(squareZeros)
        }
    }
}

private fun SquareFromZeroPoint.area(): Double = (x * x + y + y).toDouble()

data class SquareFromZeroPoint(val x: Long, val y: Long)

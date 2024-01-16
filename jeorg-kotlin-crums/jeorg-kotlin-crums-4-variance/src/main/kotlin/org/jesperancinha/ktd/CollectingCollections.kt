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
            println(squareZeros.sortedBy { it.area() })
            val newList = squareZeros.toMutableList()
            newList.sortBy { it.area() }
            println(newList)
        }
    }
}

private fun SquareFromZeroPoint.area(): Double = (x * x + y + y).toDouble()

data class SquareFromZeroPoint(val x: Long, val y: Long)

package org.jesperancinha.ktd

import java.util.Collections.sort

class CollectingCollections {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val mHullList = listOf(SquareFromZeroPoint(4, 2), SquareFromZeroPoint(2, 2))
            sort(mHullList) { lhs, rhs ->
                lhs.area().compareTo(rhs.area())
            }
            println(mHullList)
        }
    }
}

private fun SquareFromZeroPoint.area(): Double = (x * x + y + y).toDouble()

data class SquareFromZeroPoint(val x: Long, val y: Long)

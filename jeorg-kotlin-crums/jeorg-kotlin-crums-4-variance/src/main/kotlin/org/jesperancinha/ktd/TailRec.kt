package org.jesperancinha.ktd

class TailRec {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println(fibonnaci(5))
            println(fibonnaciOptimized(5))
        }

        fun fibonnaci(value: Int): Int {
            if (value == 2) return 1
            if (value == 1) return 0
            return fibonnaci(value - 1) +
                    fibonnaci(value - 2)
        }

        tailrec fun fibonnaciOptimized(value: Int,
                                       curr: Int = 1,
                                       sum: Int = 0): Int {
            if (value == 1)
                return sum
            return fibonnaciOptimized(value - 1,
                    sum, sum + curr)
        }
    }
}
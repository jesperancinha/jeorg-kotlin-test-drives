package org.jesperancinha.ktd.execs

import java.lang.Exception
import java.math.BigInteger


class LazyRiddle(
    private val radius: Int = 10,
    private var amount: Int = 5
) {

    lateinit var test: BigInteger

    fun calculate(): Double {
        return Math.PI * radius * radius * amount * test.intValueExact()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = try {
            val lazyRiddle = LazyRiddle()
            print(lazyRiddle.calculate())
            lazyRiddle.test = BigInteger.valueOf(1000)
            print(lazyRiddle.calculate())
        } catch (_: Exception) {
        }
    }
}

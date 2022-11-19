package org.jesperancinha.ktd.execs


class LazyRiddle2(
    private val radius: Int = 10,
    private var amount: Int = 5
) {

    private val test: Int by lazy {
        print(amount)
        amount = 20
        10
    }

    fun calculate(): Double {
        return Math.PI * radius * radius * amount * test
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = try {
            val lazyRiddle = LazyRiddle2()
            print(lazyRiddle.calculate())
            print(lazyRiddle.calculate())
        } catch (_: Exception) {
        }
    }
}

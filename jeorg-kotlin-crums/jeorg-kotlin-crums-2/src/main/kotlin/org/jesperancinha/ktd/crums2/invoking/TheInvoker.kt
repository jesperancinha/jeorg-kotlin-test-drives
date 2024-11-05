package org.jesperancinha.ktd.crums2.invoking

class TheInvoker {
    companion object{
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            class Adder(val x: Int) {
                operator fun invoke(y: Int): Int {
                    return x + y
                }
            }

            val addFive = Adder(5)
            println(addFive(3))
        }
    }
}
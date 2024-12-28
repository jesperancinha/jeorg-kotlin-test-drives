package org.jesperancinha.ktd.crums2

class TheNothingReturns {
    companion object {

        fun fail(message: String): Nothing {
            throw IllegalArgumentException(message)
        }

        fun infiniteLoop(): Nothing {
            while (true) {
                println("Looping forever...")
                throw RuntimeException("I'm going to fail!")
            }
        }

        fun describe(obj: Any): String = when (obj) {
            is String -> "It's a string"
            is Int -> "It's an integer"
            else -> fail("Unknown type!")
        }

        fun getValue(flag: Boolean): String {
            return if (flag) "Value" else fail("No value available!")
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println("Before failure")
            runCatching {
                fail("This is an error!")
            }.onFailure {
                println("I caught an error: ${it.message}")
            }
            runCatching {
                infiniteLoop()
            }.onFailure {
                println("I caught an error: ${it.message}")
            }
            runCatching {
                val result: String = fail("This will fail")
                println(result)
            }.onFailure {
                println("I caught an error: ${it.message}")
            }
            val x: Nothing? = null
            println(x)
            println("After failure")

            println(describe(1))
            println(describe("Hello"))
            runCatching {
                println(describe(true))
            }.onFailure {
                println("I caught an error: ${it.message}")
            }
            println(getValue(true))
            runCatching {
                println(getValue(false)) }.onFailure {
                println("I caught an error: ${it.message}")
            }
        }
    }
}
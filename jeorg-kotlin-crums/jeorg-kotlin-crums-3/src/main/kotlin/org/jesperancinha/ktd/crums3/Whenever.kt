package org.jesperancinha.ktd.crums3

class Whenever {
    // https://kotlinlang.org/docs/whatsnew21.html
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runCatching {
                val x = 5
                when (x) {
                    1 -> println("One")
                    2 -> println("Two")
                }
            } .onFailure {
                println(it)
            }

            runCatching {
                val x = 5
                val result = when (x) {
                    1 -> "One"
                    2 -> "Two"
                    else -> "Other"
                }
                println(result)
            }.onFailure {
                println(it)
            }
        }
    }
}
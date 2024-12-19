package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class FlowOperations {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val flow1 = flow {
                var value = 1
                while (value <= 5) {
                    emit(value)
                    delay(300)
                    value++
                }
            }

            val flow2 = flow {
                val letters = listOf("A", "B", "C", "D", "E")
                for (letter in letters) {
                    emit(letter)
                    delay(500)
                }
            }

            val combinedFlow = flow1.combine(flow2) { number, letter ->
                "Number: $number, Letter: $letter"
            }

            measureTimeMillis {
                combinedFlow
                    .filter { it.contains("A") }
                    .map { "value: $it" }
                    .transform {
                        if (it.contains("1"))
                            emit("A")
                        else
                            emit("B")
                    }
                    .collect { value ->
                        println("Combined: $value")
                    }
            }.let { println("Completed in $it ms") }
        }

    }
}
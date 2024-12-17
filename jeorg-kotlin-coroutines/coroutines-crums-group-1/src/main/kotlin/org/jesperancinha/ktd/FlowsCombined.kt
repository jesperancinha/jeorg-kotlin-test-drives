package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class FlowsCombined {
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
                combinedFlow.collect { value ->
                    println("Combined: $value")
                }
            }.let { println("Completed in $it ms") }
        }

    }
}
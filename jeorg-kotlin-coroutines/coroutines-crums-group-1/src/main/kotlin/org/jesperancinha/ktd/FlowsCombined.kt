package org.jesperancinha.ktd

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class FlowsCombined {
    companion object {
        @OptIn(ExperimentalCoroutinesApi::class)
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

            val combinedFlow2 = flow1.zip(flow2) { number, letter ->
                "Number: $number, Letter: $letter"
            }
            val combinedFlow3 = flow1.flatMapMerge { number-> flow2.map { letter ->  "Number: $number, Letter: $letter" } }

            showResults(combinedFlow)
            showResults(combinedFlow2)
            showResults(combinedFlow3)
        }

        private suspend fun showResults(combinedFlow: Flow<String>) {
            println("***********************")
            measureTimeMillis {
                combinedFlow.collect { value ->
                    println("Combined: $value")
                }
            }.let { println("Completed in $it ms") }
        }

    }
}
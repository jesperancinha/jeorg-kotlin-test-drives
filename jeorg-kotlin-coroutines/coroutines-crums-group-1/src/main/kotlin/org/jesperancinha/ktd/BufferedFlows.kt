package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class BufferedFlows {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            val flow = flow {
                repeat(5) { value ->
                    println("Emitting: $value")
                    emit(value)
                }
            }.buffer(capacity = 2)

            flow.collect { value ->
                delay(1000)
                println("Collected: $value")
            }
        }
    }
}
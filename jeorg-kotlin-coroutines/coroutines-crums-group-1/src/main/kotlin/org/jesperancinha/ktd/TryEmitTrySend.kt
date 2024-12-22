package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TryEmitTrySend {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            val sharedFlow = MutableSharedFlow<String>()

            launch {
                val messages = listOf("Event 1", "Event 2", "Event 3")
                for (message in messages) {
                    println("Emitting: $message")
                    sharedFlow.tryEmit(message)
                    delay(500)
                }
            }

            repeat(2) { collectorId ->
                launch {
                    sharedFlow.collect { event ->
                        println("Collector $collectorId received: $event")
                    }
                }
            }
        }
    }
}
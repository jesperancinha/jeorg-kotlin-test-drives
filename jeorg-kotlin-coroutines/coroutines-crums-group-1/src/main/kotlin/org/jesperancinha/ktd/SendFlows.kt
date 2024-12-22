package org.jesperancinha.ktd

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

class SendFlows {
    companion object {

        fun exampleFlow(): Flow<Int> = callbackFlow {
            for (i in 1..5) {
                val result = trySend(i)
                if (result.isFailure) {
                    println("Failed to send $i")
                } else {
                    println("Sent $i")
                }
                kotlinx.coroutines.delay(500)
            }

            awaitClose { println("Flow closed") }
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            runCatching {
                withTimeout(5.seconds) {
                    exampleFlow().collect { value ->
                        println("Collected: $value")
                    }
                }
            }.onFailure {
                if (it !is TimeoutCancellationException) throw it
            }
        }
    }
}
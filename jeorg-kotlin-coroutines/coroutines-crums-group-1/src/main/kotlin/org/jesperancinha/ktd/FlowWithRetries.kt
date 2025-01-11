package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.runBlocking

class FlowWithRetries {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val flowWithRetry = flow {
                println("Emitting values...")
                emit(1)
                delay(100)
                throw Exception("An error occurred")
            }.retry(3) {
                println("Error occurred, retrying...")
                true
            }

            flowWithRetry
                .catch { e -> println("Caught exception: ${e.message}") }
                .collect { value -> println("Collected: $value") }

        }
    }
}
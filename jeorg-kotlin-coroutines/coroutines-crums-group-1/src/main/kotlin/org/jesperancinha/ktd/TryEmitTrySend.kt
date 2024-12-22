package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.time.Duration.Companion.seconds

class TryEmitTrySend {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            runCatching {
                withTimeout(5.seconds) {

                    val scope = CoroutineScope(Dispatchers.Default)

                    scope.launch {
                        val sharedFlow = MutableSharedFlow<String>(3)

                        val jobEmitter = launch {
                            val messages = listOf("Event 1", "Event 2", "Event 3")
                            for (message in messages) {
                                println("Emitting: $message")
                                sharedFlow.tryEmit(message)
                                delay(500)
                            }
                        }
                        jobEmitter.join()
                        jobEmitter.cancel()

                        val job = launch {
                            sharedFlow.collect { event ->
                                println("Collector received: $event")
                            }
                        }
                        job.join()
                        job.cancel()
                    }.join()
                    scope.cancel()
                }
            }.onFailure {
                if (it !is TimeoutCancellationException) throw it
            }
        }
    }
}
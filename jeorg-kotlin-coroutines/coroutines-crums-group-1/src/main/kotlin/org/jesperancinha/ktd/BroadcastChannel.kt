package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

class BroadcastChannel {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            runCatching {
                withTimeout(10.seconds) {
                    val sharedFlow = MutableSharedFlow<Int>(replay = 0)
                    launch {
                        sharedFlow.collect { value ->
                            println("Subscriber 1 received: $value")
                        }
                    }

                    launch {
                        sharedFlow.collect { value ->
                            println("Subscriber 2 received: $value")
                        }
                    }

                    repeat(5) {
                        sharedFlow.emit(it)
                        delay(100)
                    }
                }
            }
        }
    }
}
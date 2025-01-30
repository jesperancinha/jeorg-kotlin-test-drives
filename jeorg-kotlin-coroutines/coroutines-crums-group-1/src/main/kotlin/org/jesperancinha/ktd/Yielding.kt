package org.jesperancinha.ktd

import kotlinx.coroutines.*

class Yielding {
    companion object {

        suspend fun noYieldIterations() = coroutineScope {
            launch {
                repeat(3) {
                    println("Coroutine 1 - Iteration $it")
                }
            }

            launch {
                repeat(3) {
                    println("Coroutine 2 - Iteration $it")
                }
            }
        }

        suspend fun yieldIterations() = coroutineScope {
            launch {
                repeat(3) {
                    println("Coroutine 1 - Iteration $it")
                    yield()
                }
            }

            launch {
                repeat(3) {
                    println("Coroutine 2 - Iteration $it")
                    yield()
                }
            }
        }
        @OptIn(DelicateCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            noYieldIterations().join()
            yieldIterations().join()
            GlobalScope.launch {
                println("GlobalScope.launch thread: ${Thread.currentThread().name}")
            }
        }
    }
}
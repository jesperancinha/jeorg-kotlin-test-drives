package org.jesperancinha.ktd

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

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
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            noYieldIterations().join()
            yieldIterations().join()
        }
    }
}
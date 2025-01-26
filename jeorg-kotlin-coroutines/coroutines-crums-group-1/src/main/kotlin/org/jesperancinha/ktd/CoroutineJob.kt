package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineJob {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(
                "Execution time took ${
                    measureTimeMillis {
                        runBlocking(Dispatchers.Default) {
                            launch {
                                println("Starting coroutine job 1")
                                delay(2000)
                                println("Finished coroutine job 1 with context ${currentCoroutineContext()} on Thread ${Thread.currentThread()}")
                            }
                            launch {
                                println("Started coroutine job 2")
                                delay(4000)
                                println("Finished coroutine job 2 with context ${currentCoroutineContext()} on Thread ${Thread.currentThread()}")
                            }
                            println("Finished runBlocking coroutine with context ${currentCoroutineContext()} on Thread ${Thread.currentThread()}")
                        }
                    }
                }ms")
        }
    }
}
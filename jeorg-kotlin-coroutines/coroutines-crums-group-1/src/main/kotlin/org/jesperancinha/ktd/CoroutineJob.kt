package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

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
                                delay(2.seconds)
                                println("Finished coroutine job 1 with context ${currentCoroutineContext()} on Thread ${Thread.currentThread()}")
                            }
                            launch {
                                println("Started coroutine job 2")
                                delay(4.seconds)
                                println("Finished coroutine job 2 with context ${currentCoroutineContext()} on Thread ${Thread.currentThread()}")
                            }
                            println("Finished runBlocking coroutine with context ${currentCoroutineContext()} on Thread ${Thread.currentThread()}")
                            report()
                            withContext(Dispatchers.Unconfined) {


                            }
                        }
                    }
                }ms")

            runBlocking {
                launch {
                    performTask(1)
                }

                launch {
                    performTask(2)
                }
            }
        }

        suspend fun performTask(n: Int) {
            for (i in 1..5) {
                println("Performing task $n iteration $i")
                println("performing")
                yield()
                println("Finished task $n iteration $i")
            }
        }
        suspend fun report() {
            println("Reported!")
            delay(1.seconds)
        }
    }
}
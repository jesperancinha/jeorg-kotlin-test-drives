package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import kotlin.system.measureTimeMillis

class CoroutineJob {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(
                "Execution time took ${
                    measureTimeMillis {
                        runBlocking {
                            launch {
                                println("Starting coroutine job 1")
                                delay(2000)
                                println("Finished coroutine job 1")
                            }
                            launch {
                                println("Started coroutine job 2")
                                delay(4000)
                                println("Finished coroutine job 2")
                            }
                        }
                    }
                }ms")
        }
    }
}
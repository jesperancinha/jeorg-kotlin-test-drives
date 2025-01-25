package org.jesperancinha.ktd

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
                                sleep(2000)
                                println("Finished coroutine job 1")
                            }
                            launch {
                                println("Started coroutine job 2")
                                sleep(4000)
                                println("Finished coroutine job 2")
                            }
                        }
                    }
                }ms")
        }
    }
}
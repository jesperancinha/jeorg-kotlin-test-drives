package org.jesperancinha.ktd

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

class StructuredConcurrency {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            measureTimeMillis {
                coroutineScope {
                    launch {
                        println("I'm coroutine 1 on thread ${Thread.currentThread()}")
                        delay(1000)
                        println(
                            "I'm coroutine 1 on thread ${
                                Thread.currentThread()
                            } and I am finishing on ${LocalDateTime.now()}"
                        )
                    }

                    launch {
                        println("I'm coroutine 2 on thread ${Thread.currentThread()}")
                        delay(1000)
                        println(
                            "I'm coroutine 2 on thread ${
                                Thread.currentThread()
                            } and I am finishing on ${LocalDateTime.now()}"
                        )
                    }

                    println("This coroutine scope is launched here! ${this.coroutineContext}")
                }
            }.let { println(it) }

            println("When the coroutineScope is finished, I can finally finish the program")
        }


    }
}
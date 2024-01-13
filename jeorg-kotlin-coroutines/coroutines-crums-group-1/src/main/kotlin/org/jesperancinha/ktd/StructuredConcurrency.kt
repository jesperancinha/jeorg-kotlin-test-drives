package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.ktd.CancellationStructuredConcurrency.Companion.testCancel2LevelBlockingLaunchBlockEvenLoop
import org.jesperancinha.ktd.CancellationStructuredConcurrency.Companion.testCancelBlockingLaunchBlockEvenLoop
import org.jesperancinha.ktd.StandardStructuredConcurrency.Companion.testBLockingLaunchBlockEvenLoop
import org.jesperancinha.ktd.StandardStructuredConcurrency.Companion.testBlockingLaunchDispatchersIO
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

class StructuredConcurrency {
    companion object {

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            testBLockingLaunchBlockEvenLoop()
            testBlockingLaunchDispatchersIO()
            testCancelBlockingLaunchBlockEvenLoop()
            testCancel2LevelBlockingLaunchBlockEvenLoop()
        }

    }

}

class CancellationStructuredConcurrency {
    companion object {
        fun testCancelBlockingLaunchBlockEvenLoop() = runBlocking {
            measureTimeMillis {
                kotlin.runCatching {
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

                        launch {
                            delay(500)
                            throw RuntimeException("Nothing runs now!")
                        }
                        println("This coroutine scope is launched here! ${this.coroutineContext}")
                    }

                }
            }.let { println("As expected, this run took only $it milliseconds to complete.") }

            println("When the coroutineScope is finished, I can finally finish the program")
        }


        fun testCancel2LevelBlockingLaunchBlockEvenLoop() = runBlocking {
            measureTimeMillis {
                kotlin.runCatching {
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
                            launch {
                                delay(500)
                                throw RuntimeException("Nothing runs now!")
                            }
                            delay(1000)
                            println(
                                "I'm coroutine 2 on thread ${
                                    Thread.currentThread()
                                } and I am finishing on ${LocalDateTime.now()}"
                            )
                        }


                        println("This coroutine scope is launched here! ${this.coroutineContext}")
                    }

                }
            }.let { println("As expected, this run took only $it milliseconds to complete.") }

            println("When the coroutineScope is finished, I can finally finish the program")
        }


    }

}

class StandardStructuredConcurrency {

    companion object {
        fun testBLockingLaunchBlockEvenLoop() = runBlocking {
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

            }.let { println("As expected, this run took only $it milliseconds to complete.") }

            println("When the coroutineScope is finished, I can finally finish the program")
        }


        fun testBlockingLaunchDispatchersIO() = runBlocking {
            measureTimeMillis {
                coroutineScope {
                    val job = launch(Dispatchers.IO) {
                        launch {
                            println("I'm coroutine 1 on thread ${Thread.currentThread()}")
                            delay(1000)
                            println(
                                "I'm coroutine 1 on thread ${
                                    Thread.currentThread()
                                } and I am finishing on ${LocalDateTime.now()}"
                            )
                            println(this.toString())
                            println(this.coroutineContext.job.key.toString())
                        }

                        launch {
                            println("I'm coroutine 2 on thread ${Thread.currentThread()}")
                            delay(1000)
                            println(
                                "I'm coroutine 2 on thread ${
                                    Thread.currentThread()
                                } and I am finishing on ${LocalDateTime.now()}"
                            )
                            println(this.toString())
                            println(this.coroutineContext.job.key.toString())
                        }

                        println(this.toString())
                        println(this.coroutineContext.job.key.toString())

                    }
                    println("--------")
                    println(job.key.toString())
                    println(this.toString())
                    println(this.coroutineContext.job.key.toString())

                    println("This coroutine scope is launched here! ${this.coroutineContext}")
                }
            }.let { println(it) }

            println("When the coroutineScope is finished, I can finally finish the program")
        }

    }
}
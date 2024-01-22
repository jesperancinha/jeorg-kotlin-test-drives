package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.ktd.CancellationStructuredConcurrency.Companion.testCancel2LevelBlockingLaunchBlockEvenLoop
import org.jesperancinha.ktd.CancellationStructuredConcurrency.Companion.testCancelBlockingLaunchBlockEvenLoop
import org.jesperancinha.ktd.CancellationStructuredConcurrency.Companion.testCancelBlockingLaunchDispatchersIO
import org.jesperancinha.ktd.StandardStructuredConcurrency.Companion.logCoroutinesWithADelayOf
import org.jesperancinha.ktd.StandardStructuredConcurrency.Companion.testBLockingLaunchBlockEvenLoop
import org.jesperancinha.ktd.StandardStructuredConcurrency.Companion.testBlockingLaunchDispatchersIO
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

class StructuredConcurrency {
    companion object {

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            launchTest(1, "should show blocking event loop") { testBLockingLaunchBlockEvenLoop() }
            launchTest(2, "should show Dispatchers.IO as dispatcher") { testBlockingLaunchDispatchersIO() }
            launchTest(
                3,
                "should cancel the whole scope when failing coroutine on the same level"
            ) { testCancelBlockingLaunchBlockEvenLoop() }
            launchTest(
                4,
                "should cancel the whole scope when failing coroutine one level below"
            ) { testCancel2LevelBlockingLaunchBlockEvenLoop() }
            launchTest(
                5,
                "should cancel the whole scope when failing a Dispatchers.IO context"
            ) { testCancelBlockingLaunchDispatchersIO() }
        }

    }

}

fun launchTest(id: Long, testName: String, f: () -> Unit) {
    println("********************************************************************************************");
    println("\nStarting test ($id): \"$testName\" at ${LocalDateTime.now()}")
    println("The whole test ($id) took ${measureTimeMillis { f() }} milliseconds to complete!")
    println("\nTest ($id) \"$testName finished at ${LocalDateTime.now()}")
    println("********************************************************************************************");
}

class CancellationStructuredConcurrency {
    companion object {
        fun testCancelBlockingLaunchBlockEvenLoop(): Unit = runBlocking {
            measureTimeMillis {
                runCatching {
                    coroutineScope {
                        launch {
                            logCoroutinesWithADelayOf(1, 1000)
                        }
                        launch {
                            logCoroutinesWithADelayOf(2, 1000)
                        }
                        launch {
                            delay(500)
                            throw RuntimeException("Nothing runs now!")
                        }
                        println("This coroutine scope is launched here! $coroutineContext")
                    }
                }.onFailure {
                    println("This scope was cancelled!")
                }
            }.let { println("As expected, this run took only $it milliseconds to complete.") }
            println("When the coroutineScope is finished, I can finally finish the program")
        }


        fun testCancel2LevelBlockingLaunchBlockEvenLoop() = runBlocking {
            measureTimeMillis {
                runCatching {
                    coroutineScope {

                        launch {
                            logCoroutinesWithADelayOf(1, 1000)
                        }

                        launch {
                            launch {
                                delay(500)
                                throw RuntimeException("Nothing runs now!")
                            }
                            logCoroutinesWithADelayOf(2, 1000)
                        }
                        println("This coroutine scope is launched here! ${this.coroutineContext}")
                    }

                }
            }.let { println("As expected, this run took only $it milliseconds to complete.") }
            println("When the coroutineScope is finished, I can finally finish the program")
        }


        fun testCancelBlockingLaunchDispatchersIO() = runBlocking(Dispatchers.IO) {
            measureTimeMillis {
                runCatching {
                    coroutineScope {
                        launch {
                            logCoroutinesWithADelayOf(1, 1000)
                        }

                        launch {
                            logCoroutinesWithADelayOf(2, 1000)
                        }
                        launch {
                            delay(500)
                            throw RuntimeException("Nothing runs now!")
                        }

                        println("--------")
                        println(this.toString())
                        println(this.coroutineContext.job.key.toString())

                        println("This coroutine scope is launched here! ${this.coroutineContext}")
                    }
                }.onFailure {
                    println("This scope was cancelled!")
                }
            }.let { println(it) }
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
                        logCoroutinesWithADelayOf(id = 1, delay = 1_000)
                    }
                    launch {
                        logCoroutinesWithADelayOf(id = 2, delay = 1_000)
                    }
                    println("This coroutine scope is launched here! ${this.coroutineContext}")
                }
            }.let { println("As expected, this run took only $it milliseconds to complete.") }
            println("When the coroutineScope is finished, I can finally finish the program")
        }


        fun testBlockingLaunchDispatchersIO() = runBlocking(Dispatchers.IO) {
            measureTimeMillis {
                val job1 = launch {
                    logCoroutinesWithADelayOf(1, 1000)
                }

                val job2 = launch {
                    logCoroutinesWithADelayOf(2, 1000)
                }

                println("--------")
                println(this.toString())
                println(this.coroutineContext.job.key.toString())

                println("This coroutine scope is launched here! ${this.coroutineContext}")
                job1.join()
                job2.join()
            }.let { println(it) }
            println("When the coroutineScope is finished, I can finally finish the program")
        }

        suspend fun logCoroutinesWithADelayOf(id: Long, delay: Long) = coroutineScope {
            println("($id) I'm coroutine 1 on thread ${Thread.currentThread()}")
            delay(delay)
            println(
                "($id) I'm coroutine 1 on thread ${
                    Thread.currentThread()
                } and I am finishing on ${LocalDateTime.now()}"
            )
            println("($id) Coroutine 1  is $this")
            println("($id) The context of coroutine 1 is ${this.coroutineContext}")
            println("($id) The job key of coroutine 1 is ${this.coroutineContext.job.key}")
        }

    }
}
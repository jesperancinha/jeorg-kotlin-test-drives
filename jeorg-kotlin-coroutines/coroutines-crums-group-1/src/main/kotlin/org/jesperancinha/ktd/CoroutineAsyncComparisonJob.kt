package org.jesperancinha.ktd

import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

object CoroutineAsyncComparisonJob {
    @JvmStatic
    fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
        println("Starting coroutine job")
        val launchMs = measureTimeMillis {
            val job1 = launch {
                println("Started coroutine job 1 - ${Thread.currentThread()} - Current Context: ${currentCoroutineContext()}")
                delay(2.seconds)
                println("Finished coroutine job 1")
            }
            val job2 = launch {
                println("Started coroutine job 2 - ${Thread.currentThread()} - Current Context: ${currentCoroutineContext()}")
                delay(5.seconds)
                println("Finished coroutine job 2")
            }
            job1.join()
            job2.join()
        }.apply { println("Finished launch example in $this ms") }

        val asyncExampleMs = measureTimeMillis {
            val deferred1 = async {
                println("Started coroutine job 1 - ${Thread.currentThread()} - Current Context: ${currentCoroutineContext()}")
                delay(2.seconds)
                println("Finished coroutine job 1")
                "Coroutine 1 result"
            }
            val deferred2 = async {
                println("Started coroutine job 2 - ${Thread.currentThread()} - Current Context: ${currentCoroutineContext()}")
                delay(5.seconds)
                println("Finished coroutine job 2")
                "Coroutine 2 result"
            }
            val result1 = deferred1.await()
            val result2 = deferred2.await()
            println("Finished async example with results $result1 and $result2")
        }.apply { println("Finished async example in $this ms") }

        println("It took ${launchMs + asyncExampleMs}  milliseconds to run both examples")
    }
}
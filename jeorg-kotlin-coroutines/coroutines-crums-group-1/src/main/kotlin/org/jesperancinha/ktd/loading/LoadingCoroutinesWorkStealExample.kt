package org.jesperancinha.ktd.loading

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.system.*

object LoadingCoroutinesWorkStealExample {

    @JvmStatic
    fun main(args: Array<String> = emptyArray()) = runBlocking {
        val numCores = Runtime.getRuntime().availableProcessors()
        println("Available CPU cores: $numCores")
        val jobs = mutableListOf<Job>()
        val time = measureTimeMillis {
            repeat(numCores * 2) { index ->
                jobs += launch(Dispatchers.Default) {
                    val threadName = Thread.currentThread().name
                    val duration = if (index % 2 == 0) 1000L else 500L // Some tasks take longer
                    println("Coroutine $index running on $threadName for $duration ms")
                    sleep(duration)
                    println("Coroutine $index finished on $threadName")
                }
            }
            jobs.joinAll()
        }

        println("Total execution time: $time ms")
    }

}
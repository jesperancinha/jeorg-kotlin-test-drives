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
                    val duration = if (index % 2 == 0) 1000L else 500L
                    println("Coroutine $index running on ${Thread.currentThread().name} for $duration ms")
                    delay(duration)
                    println("Coroutine $index finished on ${Thread.currentThread().name}")
                }
            }
            jobs.joinAll()
        }

        println("Total execution time: $time ms")
    }

}
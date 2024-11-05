package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

object Main {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        println("Hello world!")
        supervisorScope {
            val job1 = launch {
                delay(1000)
//                cancel(CancellationException("Job is closed"))
                throw RuntimeException("Failure in job 1")
//                println("Job 1 completed successfully")
            }

            val job2 = launch {
                delay(2000)
                println("Job 2 completed successfully")
            }
            joinAll(job1, job2)
        }
    }
}
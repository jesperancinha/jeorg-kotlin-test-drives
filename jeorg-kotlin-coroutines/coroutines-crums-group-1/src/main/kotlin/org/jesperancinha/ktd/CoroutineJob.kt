package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineJob {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()){
            cancelTest()
            checkActiveTest()
            checkIsCompleted()
            checkHandler()
            checkLazyStart()
        }

        fun cancelTest() = runBlocking {
            val job = launch {
                delay(100)
            }
            job.cancel()
            if (job.isCancelled) {
                println("Job is cancelled")
            }
        }

        fun checkActiveTest() = runBlocking {
            val job = launch {
                delay(100)
            }
            if (job.isActive) {
                println("Job is still running")
            }
        }

        fun checkIsCompleted() = runBlocking {
            val job = launch {
                delay(100)
            }
            job.join()
            if (job.isCompleted) {
                println("Job has completed")
            }
        }

        fun checkHandler()= runBlocking {
            val job = launch {
               delay(100)
            }
            job.invokeOnCompletion { throwable ->
                if (throwable != null) {
                    println("Job was canceled due to ${throwable.message}")
                }
            }
            job.cancel()
            job.join()
        }
        fun checkLazyStart() = runBlocking {
            val job = launch(start = CoroutineStart.LAZY) {
                println("Task Started")
                delay(200)
            }
            delay(300)
            println("Task not Started")
            job.start()
            job.join()
        }
    }
}
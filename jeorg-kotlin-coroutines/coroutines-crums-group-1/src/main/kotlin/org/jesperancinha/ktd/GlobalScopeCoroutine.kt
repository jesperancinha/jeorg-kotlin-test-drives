package org.jesperancinha.ktd

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

class GlobalScopeCoroutine {
    companion object {
        @OptIn(DelicateCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val job = GlobalScope.launch {
                delay(1.seconds.toJavaDuration())
            }
            println("Global >> Is the Global job cancelled? ${job.isCancelled}")
            println("Global >> Is the Global job active? ${job.isActive}")
            println("Global >> Is the Global job completed? ${job.isCompleted}")
            job.cancel()
            println("Global >> Is the Global job cancelled after cancel? ${job.isCancelled}")
            runBlocking {
                val jobInScope = launch {
                    delay(1.seconds.toJavaDuration())

                }
                jobInScope.cancel()
                println("First Job >> Is this job cancelled? ${jobInScope.isCancelled}")
            }
            val lastJob = runBlocking {
                val jobInScope = launch {
                    delay(1.seconds.toJavaDuration())

                }
                println("Second Job >> Is this job cancelled? ${jobInScope.isCancelled}")
                println("Second Job >> Is this job active? ${jobInScope.isActive}")
                println("Second Job >> Is this job completed? ${jobInScope.isCompleted}")
                jobInScope
            }
            println("Second Job After Life-Cycle >> Is this job cancelled? ${lastJob.isCancelled}")
            println("Second Job After Life-Cycle >> Is this job active? ${lastJob.isActive}")
            println("Second Job After Life-Cycle >> Is this job completed? ${lastJob.isCompleted}")
        }
    }
}
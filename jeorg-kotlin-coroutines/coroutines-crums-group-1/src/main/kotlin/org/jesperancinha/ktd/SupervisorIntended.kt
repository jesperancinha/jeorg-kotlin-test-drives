package org.jesperancinha.ktd

import kotlinx.coroutines.*
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

class SupervisorIntended {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            measureTimeMillis { testSuspendFunction() }.logDuration("-- test suspend function blocking --")
            measureTimeMillis {
                CoroutineScope(Dispatchers.IO).launch {
                    testSuspendFunction()
                }.join()
            }.logDuration("-- test IO function ---")
            measureTimeMillis {
                CoroutineScope(Dispatchers.Unconfined).launch {
                    testSuspendFunction()
                }.join()
            }.logDuration("-- test Unconfined function ---")
            measureTimeMillis {
                CoroutineScope(Dispatchers.Default).launch {
                    testSuspendFunction()
                }.join()
            }.logDuration("-- test Default function ---")

        }

        private suspend fun CoroutineScope.testSuspendFunction() {
            println("**** Starting test suspend function at ${LocalDateTime.now()}")
            supervisorScope {
                println(this.coroutineContext)
                val launch = launch {
                    delay(1000)
                    throw RuntimeException("I have an error and this is it!")
                }
                println("The type is ${launch.job::class.java}")
                launch {
                    delay(2000)
                    println("Nothing wrong here")
                }
            }
            println(this.coroutineContext)
            delay(1000)
            println("And I keep going...")
            println("****** Ending test suspend function at ${LocalDateTime.now()}")

        }
    }
}
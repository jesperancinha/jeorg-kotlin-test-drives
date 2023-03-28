package org.jesperancinha.ktd

import kotlinx.coroutines.*

class SupervisorIntended {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            testSuspendFunction()
            CoroutineScope(Dispatchers.IO).launch {
                testSuspendFunction()
            }
            delay(4000)
        }

        private suspend fun CoroutineScope.testSuspendFunction() {
            supervisorScope {
                println(this.coroutineContext)
                launch {
                    delay(1000)
                    throw RuntimeException("I have an error and this is it!")
                }
                launch {
                    delay(2000)
                    println("Nothing wrong here")
                }
            }
            println(this.coroutineContext)
            delay(1000)
            println("And I keep going...")
        }
    }
}
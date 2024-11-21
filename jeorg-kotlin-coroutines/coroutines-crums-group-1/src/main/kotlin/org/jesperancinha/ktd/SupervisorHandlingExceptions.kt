package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class SupervisorHandlingExceptions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("Caught exception: ${exception.message}")
            }
            supervisorScope {
                launch(handler) {
                    println("Child coroutine started")
                    throw IllegalStateException("Child coroutine failed!")
                }
            }
        }
    }
}
package org.jesperancinha.ktd

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SuspendCoroutine {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): kotlin.Unit = runBlocking(Dispatchers.IO) {
            val first = launch {
                coroutineScope {
                    delay(2000)
                    println("This is the last. We can only delay from a coroutine body.")
                }
            }
            launch {
                suspendCoroutine<Unit> {
                    sleep(1000)
                    println("This is second")
                    it.resume(Unit)
                }
            }
            launch {
                println("This is the first")
            }
            first.join()
        }
    }
}
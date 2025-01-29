package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

class MySuspendFunctionJob {
    companion object {

        suspend fun myCustomSuspendFunction(n: Int) = suspendCoroutine<Unit> { continuation ->
            println("Before suspension $n")

            Thread {
                println("Doing something $n")
                Thread.sleep(1000)
                println("After sleeping $n")
                continuation.resume(Unit)
                Thread.sleep(1000)
                println("The coroutine finishes but main is already complete $n")
            }.start()

            println("Coroutine suspended, waiting for manual resume $n")
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit {
            println("It took ${measureTimeMillis {
                runBlocking {
                    println("Start")
                    myCustomSuspendFunction(1)
                    myCustomSuspendFunction(2)
                    println("End")
                }
            }} ms to run")
        }
    }
}
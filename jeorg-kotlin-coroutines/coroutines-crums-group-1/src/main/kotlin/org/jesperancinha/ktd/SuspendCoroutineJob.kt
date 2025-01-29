package org.jesperancinha.ktd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

class SuspendCoroutineJob {
    companion object {

        suspend fun myCustomSuspendFunction(n: Int) = suspendCoroutine<Unit> { continuation ->
            println("Before suspension $n => ${Thread.currentThread()} with ${continuation.context}")
            Thread {
                println("Doing something $n => ${Thread.currentThread()} with ${continuation.context}")
                Thread.sleep(1000)
                println("After sleeping $n => ${Thread.currentThread()} with ${continuation.context}")
                continuation.resume(Unit)
                Thread.sleep(1000)
                println("The coroutine finishes but main is already complete $n => ${Thread.currentThread()} with ${continuation.context}")
            }.start()

            println("Coroutine suspended, waiting for manual resume $n => ${Thread.currentThread()} with ${continuation.context}")
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit {
            println("It took ${measureTimeMillis {
                runBlocking(Dispatchers.Default) {
                    println("Start => ${Thread.currentThread()} with ${currentCoroutineContext()}")
                    myCustomSuspendFunction(1)
                    myCustomSuspendFunction(2)  
                    println("End => ${Thread.currentThread()} with ${currentCoroutineContext()}")
                    Thread.sleep(2000)
                }
            }} ms to run")
        }
    }
}
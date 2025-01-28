package org.jesperancinha.ktd

import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MySuspendFunctionJob {
    companion object {

        suspend fun myCustomSuspendFunction() = suspendCoroutine<Unit> { continuation ->
            println("Before suspension")

            Thread {
                Thread.sleep(1000)
                continuation.resume(Unit)
            }.start()

            println("Coroutine suspended, waiting for manual resume")
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            println("Start")
            myCustomSuspendFunction()
            println("End")
        }
    }
}
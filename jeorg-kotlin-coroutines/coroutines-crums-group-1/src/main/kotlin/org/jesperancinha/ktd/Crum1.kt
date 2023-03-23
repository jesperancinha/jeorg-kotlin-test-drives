package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.time.LocalDateTime

private const val WAIT_FOR_REPORT: Long = 3000

private const val WAIT_BETWEEN_TASKS: Long = 1000

private const val WAIT_FOR_PROCESS: Long= 5000

object SpecialLogger {
    fun info(logText: Any) = ConsolerizerComposer.outSpace()
        .orange().doubleFrame(logText.toString()).toConsoleLn()
}

class CancellationWithException {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            testLaunch()
            testRemove()
            testAsync()
            testAsyncAndWait()
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun testRemove() = runBlocking {
    SpecialLogger.info("Tests without changing scope")
    GlobalScope.launch {
        try {
            launch {
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }.join()
        } catch (e: RuntimeException) {
            reportException(e)
        }
        launch {
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
}


@OptIn(DelicateCoroutinesApi::class)
fun testLaunch() = runBlocking {
    SpecialLogger.info("Tests with changing to scope IO and join")
    GlobalScope.launch {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }.join()
        } catch (e: RuntimeException) {
            reportException(e)
        }
        launch {
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
}

@OptIn(DelicateCoroutinesApi::class)
fun testAsync() = runBlocking {
    SpecialLogger.info("Tests with changing to scope IO, asynchronous")
    GlobalScope.launch {
        try {
            CoroutineScope(Dispatchers.IO).async {
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }
        } catch (e: RuntimeException) {
            reportException(e)
        }
        launch {
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
}

@OptIn(DelicateCoroutinesApi::class)
fun testAsyncAndWait() = runBlocking {
    SpecialLogger.info("Tests with changing to scope IO, asynchronous and wait")
    GlobalScope.launch {
        try {
            CoroutineScope(Dispatchers.IO).async {
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }.await()
        } catch (e: RuntimeException) {
            reportException(e)
        }
        launch {
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(2 * WAIT_FOR_PROCESS)
}

fun generateException(): Nothing = throw RuntimeException("An error has been generated!")

fun completeReport() = println("Report has been completed")

fun reportException(e: RuntimeException) =
    println("An error has been reported! ${e.stackTraceToString()}")

private fun logTimestamp() =
    println("The time is now ${LocalDateTime.now()}")


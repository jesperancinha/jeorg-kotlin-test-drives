package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.Processes.*
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis
import kotlin.coroutines.CoroutineContext

private const val WAIT_FOR_REPORT: Long = 3000

private const val WAIT_BETWEEN_TASKS: Long = 1000

private const val WAIT_FOR_PROCESS: Long = 5000

enum class Processes {
    MACHINE_START,
    CHEESE_CURD_MAKING,
    LET_IT_SIT,
    MAKE_REPORT
}

object SpecialLogger {
    fun info(logText: Any) = ConsolerizerComposer.outSpace()
        .orange().doubleFrame(logText.toString()).toConsoleLn()
}

class CancellationWithException {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            measureTimeMillis { testLaunch() }.logDuration("testLaunch")
            measureTimeMillis { testRemove() }.logDuration("testRemove")
            measureTimeMillis { testAsync() }.logDuration("testAsync")
            measureTimeMillis { testAsyncAndWait() }.logDuration("testAsyncAndWait")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun testLaunch() = runBlocking {
    SpecialLogger.info("Tests with changing to context IO and join")
    GlobalScope.launch {
        logContext(this.coroutineContext, MACHINE_START)
        runCatching {
            CoroutineScope(Dispatchers.IO).launch {
                logContext(this.coroutineContext, CHEESE_CURD_MAKING)
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    logContext(this.coroutineContext, LET_IT_SIT)
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }.join()
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            logContext(this.coroutineContext, MAKE_REPORT)
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
    completeProcess()
}

@OptIn(DelicateCoroutinesApi::class)
fun testRemove() = runBlocking {
    SpecialLogger.info("Tests without changing scope")
    GlobalScope.launch {
        logContext(this.coroutineContext, MACHINE_START)
        runCatching {
            launch {
                logContext(this.coroutineContext, CHEESE_CURD_MAKING)
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    logContext(this.coroutineContext, LET_IT_SIT)
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }.join()
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            logContext(this.coroutineContext, MAKE_REPORT)
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
    completeProcess()
}

@OptIn(DelicateCoroutinesApi::class)
fun testAsync() = runBlocking {
    SpecialLogger.info("Tests with changing to context IO, asynchronous")
    GlobalScope.launch {
        logContext(this.coroutineContext, MACHINE_START)
        runCatching {
            CoroutineScope(Dispatchers.IO).async {
                logContext(this.coroutineContext, CHEESE_CURD_MAKING)
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    logContext(this.coroutineContext, LET_IT_SIT)
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            logContext(this.coroutineContext, MAKE_REPORT)
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
    completeProcess()
}

@OptIn(DelicateCoroutinesApi::class)
fun testAsyncAndWait() = runBlocking {
    SpecialLogger.info("Tests with changing to context IO, asynchronous and wait")
    GlobalScope.launch {
        logContext(this.coroutineContext, MACHINE_START)
        runCatching {
            CoroutineScope(Dispatchers.IO).async {
                logContext(this.coroutineContext, CHEESE_CURD_MAKING)
                delay(WAIT_BETWEEN_TASKS)
                logTimestamp()
                launch {
                    logContext(this.coroutineContext, LET_IT_SIT)
                    delay(WAIT_BETWEEN_TASKS)
                    logTimestamp()
                    generateException()
                }
            }.await()
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            logContext(this.coroutineContext, MAKE_REPORT)
            delay(WAIT_FOR_REPORT)
            completeReport()
        }
    }
    delay(2 * WAIT_FOR_PROCESS)
    completeProcess()
}

fun logContext(coroutineScope: CoroutineContext, processes: Processes) =
    println("Process $processes is running in $coroutineScope")

fun generateException(): Nothing = throw RuntimeException("An error has been generated!")

fun completeReport() = println("Report has been completed")

fun completeProcess() = println("Process has been completed!")

fun reportException(e: RuntimeException) =
    println("An error has been reported! ${e.stackTraceToString()}")

fun Long.logDuration(process:String) = println("Process $process took $this ms to complete!")

private fun logTimestamp() =
    println("The time is now ${LocalDateTime.now()}")


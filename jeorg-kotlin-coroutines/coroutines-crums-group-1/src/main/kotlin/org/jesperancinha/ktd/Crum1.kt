package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.Processes.*
import java.time.LocalDateTime
import kotlin.coroutines.CoroutineContext

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
            testLaunch()
            testRemove()
            testAsync()
            testAsyncAndWait()
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun testLaunch() = runBlocking {
    GlobalScope.launch {
        runCatching {
            CoroutineScope(Dispatchers.IO).launch {
                delay(1000)
                launch {
                    delay(1000)
                    generateException()
                }
            }.join()
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            delay(3000)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
    completeProcess()
}

@OptIn(DelicateCoroutinesApi::class)
fun testRemove() = runBlocking {
    GlobalScope.launch {
        runCatching {
            launch {
                delay(1000)
                launch {
                    delay(1000)
                    generateException()
                }
            }.join()
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            delay(3000)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
    completeProcess()
}

@OptIn(DelicateCoroutinesApi::class)
fun testAsync() = runBlocking {
    GlobalScope.launch {
        runCatching {
            CoroutineScope(Dispatchers.IO).async {
                delay(1000)
                launch {
                    delay(1000)
                    generateException()
                }
            }
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            delay(3000)
            completeReport()
        }
    }
    delay(WAIT_FOR_PROCESS)
    completeProcess()
}

@OptIn(DelicateCoroutinesApi::class)
fun testAsyncAndWait() = runBlocking {
    GlobalScope.launch {
        runCatching {
            CoroutineScope(Dispatchers.IO).async {
                delay(1000)
                launch {
                    delay(1000)
                    generateException()
                }
            }.await()
        }.onFailure {
            reportException(it as RuntimeException)
        }
        launch {
            delay(3000)
            completeReport()
        }
    }
    delay(2 * WAIT_FOR_PROCESS)
    completeProcess()
}

fun generateException(): Nothing = throw RuntimeException("An error has been generated!")

fun completeReport() = println("Report has been completed")

fun completeProcess() = println("Process has been completed!")

fun reportException(e: RuntimeException) =
    println("An error has been reported! ${e.stackTraceToString()}")



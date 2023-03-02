package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.Unconfined
import org.apache.commons.lang3.ThreadUtils
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.io.File
import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

object CoroutinesTestLauncher {
    private val logger = object {
        fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
        fun infoTs(logText: Any?) = ConsolerizerComposer.out().red(logText)
        fun infoText(logText: Any?) = ConsolerizerComposer.out().green(logText)
        fun infoTextSeparator(logText: Any?) = ConsolerizerComposer.out().orange(ConsolerizerComposer.title(logText))
        fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title(logText))
    }

    @JvmStatic
    fun main(args: Array<String> = emptyArray()) = runBlocking {
        logger.infoTitle(
            "Coroutines scope tests / There are currently ${
                Runtime.getRuntime().availableProcessors()
            } CPU's available"
        )

        logger.info("Starting simple tests")
        runUnconfinedCoroutinesTest()
        runIOCoroutinesTest()
        runDefaultCoroutinesTest()
        runMainCoroutinesTest()

        logger.info("Starting load tests")
        measureTimeMillis { runDefaultLoadCoroutinesTest() }.also { logger.infoTs("DEFAULT took $it milliseconds") }
        measureTimeMillis { runIOLoadCoroutinesTest() }.also { logger.infoTs("IO took $it milliseconds") }
        Unit
    }

    private suspend fun runDefaultLoadCoroutinesTest() {
        logger.infoTextSeparator("Default means that the coroutine will execute concurrently with other child/sub coroutines and distributed among different threads.")
        logger.infoTextSeparator("Blocking IO tasks ARE NOT offloaded to a pool of threads")
        val job = CoroutineScope(Default).launch {
            repeat(10) {
                launch {
                    doSomeIOStuff()
                    logger.info("This is cat @ ${LocalDateTime.now()} reading Files ${doSomeIOStuff()}")
                }
                launch {
                    doSomeIOStuff()
                    logger.info("This is mouse @ ${LocalDateTime.now()} reading Files ${doSomeIOStuff()}")
                }
            }
            doSomeIOStuff()
            logger.info("This is master @ ${LocalDateTime.now()} reading Files ${doSomeIOStuff()}")
        }
        reportCurrentThreads()
        job.join()
    }

    private suspend fun runIOLoadCoroutinesTest() {
        logger.infoTextSeparator("IO means that the coroutine will execute concurrently with other child/sub coroutines and distributed among different threads. ")
        logger.infoTextSeparator("The maximum number of system threads can be raised up to 64. Blocking IO tasks ARE offloaded to a pool of threads")
        val job = CoroutineScope(IO).launch {
            repeat(10) {
                launch {
                    doSomeIOStuff()
                    logger.info("This is cat @ ${LocalDateTime.now()} reading Files ${doSomeIOStuff()}")
                }
                launch {
                    doSomeIOStuff()
                    logger.info("This is mouse @ ${LocalDateTime.now()} reading Files ${doSomeIOStuff()}")
                }
            }
            doSomeIOStuff()
            logger.info("This is master @ ${LocalDateTime.now()} reading Files ${doSomeIOStuff()}")
        }
        reportCurrentThreads()
        job.join()
    }

    private suspend fun runMainCoroutinesTest() {
        logger.infoTextSeparator("Main means that the coroutine will execute only on one thread and only on the Main thread, whichever that thread might be")
        logger.infoTextSeparator("This will not work here")
        logger.infoTextSeparator("Main is only available in these dependencies: kotlinx-coroutines-android, kotlinx-coroutines-javafx or kotlinx-coroutines-swing")
        try {
            val job = CoroutineScope(Main).launch {
                launch {
                    delay(100)
                    logger.info("This is cat @ ${LocalDateTime.now()}")
                }
                launch {
                    logger.info("This is mouse @ ${LocalDateTime.now()}")
                }
                logger.info("This is master @ ${LocalDateTime.now()}")
            }
            reportCurrentThreads()
            job.join()
        } catch (_: Exception){
        }
    }

    private suspend fun runUnconfinedCoroutinesTest() {
        logger.infoTextSeparator("Unconfined means that the coroutine will execute immediately on the thread giving it priority over child/sub coroutines")
        val job = CoroutineScope(Unconfined).launch {
            launch {
                delay(100)
                logger.info("This is cat @ ${LocalDateTime.now()}")
            }
            launch {
                logger.info("This is mouse @ ${LocalDateTime.now()}")
            }
            logger.info("This is master @ ${LocalDateTime.now()}")
        }
        reportCurrentThreads()
        job.join()
    }

    private suspend fun runDefaultCoroutinesTest() {
        logger.infoTextSeparator("Default -> Default and IO exhibit the same behaviour if not dealing with enough launches. In this case the parent and the children run concurrently")
        val job = CoroutineScope(Default).launch {
            launch {
                delay(100)
                logger.info("This is cat @ ${LocalDateTime.now()}")
            }
            launch {
                logger.info("This is mouse @ ${LocalDateTime.now()}")
            }
            logger.info("This is master @ ${LocalDateTime.now()}")
        }
        reportCurrentThreads()
        job.join()
    }

    private suspend fun runIOCoroutinesTest() {
        logger.infoTextSeparator("IO -> Default and IO exhibit the same behaviour if not dealing with enough launches. In this case the parent and the children run concurrently")
        val job = CoroutineScope(IO).launch {
            launch {
                delay(100)
                logger.info("This is cat @ ${LocalDateTime.now()}")
            }
            launch {
                logger.info("This is mouse @ ${LocalDateTime.now()}")
            }
            logger.info("This is master @ ${LocalDateTime.now()}")
        }
        reportCurrentThreads()
        job.join()
    }

    private fun doSomeIOStuff() = File.createTempFile("temp${UUID.randomUUID()}", "txt")
        .also {
            logger.infoTs("Start -> ${LocalDateTime.now()}")
            it.writeBytes((0..1000_000).map { "a" }.joinToString(",") { text -> text }.toByteArray())
            logger.infoTs("End -> ${LocalDateTime.now()}")
            it.deleteOnExit()
        }

    private fun reportCurrentThreads() {
        logger.infoText(ThreadUtils.getAllThreads().map {
            it
        }.joinToString("\n") { it.toString() })
    }
}
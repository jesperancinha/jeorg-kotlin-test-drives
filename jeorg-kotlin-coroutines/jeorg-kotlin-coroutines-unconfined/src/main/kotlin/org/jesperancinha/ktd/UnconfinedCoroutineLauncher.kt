package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

object UnconfinedCoroutineLauncher {
    private val logger = object {
        fun info(logText: Any?) = ConsolerizerComposer.out().yellow(logText)
        fun infoBefore(logText: Any?) = ConsolerizerComposer.out().green(logText)
        fun infoAfter(logText: Any?) = ConsolerizerComposer.out().red(logText)
        fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title(logText))
    }

    @JvmStatic
    fun main(args: Array<String> = emptyArray()) = runBlocking {
        logger.infoTitle(
            "Unconfined Coroutines scope tests / There are currently ${
                Runtime.getRuntime().availableProcessors()
            } CPU's available"
        )
        CoroutineScope(Dispatchers.Unconfined).launch {
            launch {
                logger.info("Running on context $coroutineContext")
                logger.infoBefore("Siamese Cat is launching on Thread-${Thread.currentThread().name} with id ${Thread.currentThread().threadId()}")
                delay(2)
                logger.infoAfter("Siamese Cat just ran on Thread-${Thread.currentThread().name} with id ${Thread.currentThread().threadId()}")
            }
            launch {
                logger.info("Running on context $coroutineContext")
                logger.infoBefore("Mouse is launching on Thread-${Thread.currentThread().name} with id ${Thread.currentThread().threadId()}")
                delay(20)
                logger.infoAfter("Mouse just ran on Thread-${Thread.currentThread().name} with id ${Thread.currentThread().threadId()}")
            }
            launch {
                logger.info("Running on context $coroutineContext")
                logger.infoBefore("Maine Coon is launching on Thread-${Thread.currentThread().name} with id ${Thread.currentThread().threadId()}")
                delay(30)
                logger.infoAfter("Maine Coon is just ran on Thread-${Thread.currentThread().name} with id ${Thread.currentThread().threadId()}")
            }
        }.join()
    }
}
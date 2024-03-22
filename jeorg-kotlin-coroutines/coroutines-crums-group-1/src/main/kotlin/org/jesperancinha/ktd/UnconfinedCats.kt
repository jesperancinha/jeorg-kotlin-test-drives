package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.time.LocalDateTime

class UnconfinedCats {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            runUnconfinedCoroutinesTest()
        }

private suspend fun runUnconfinedCoroutinesTest() {
    logger.infoTextSeparator("Unconfined means that all coroutines will execute concurrently on the starting thread in the scope of the parent thread")
    val job = CoroutineScope(Dispatchers.Unconfined).launch {
        launch {
            logger.infoThread("Cat Before thread ==> ${Thread.currentThread()}")
            delay(100)
            logger.info("This is cat @ ${LocalDateTime.now()}")
            logger.info("Cat context $coroutineContext")
            logger.infoThread("Cat After Resume thread ==> ${Thread.currentThread()}")
        }
        launch {
            logger.info("This is mouse @ ${LocalDateTime.now()}")
            logger.info("Mouse context $coroutineContext")
        }
        logger.info("This is master @ ${LocalDateTime.now()}")
        logger.info("Master context $coroutineContext")
        logger.infoThread(Thread.currentThread())

    }
    job.join()
}

        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
            fun infoThread(logText: Any?) = ConsolerizerComposer.out().yellow(logText)
            fun infoTextSeparator(logText: Any?) = ConsolerizerComposer.out().orange(ConsolerizerComposer.title(logText))
        }
    }
}
package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.time.LocalDateTime

object Main {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        logger.infoTitle("Coroutines scope tests")
        runUnconfinedCoroutinesTest()
    }

    private val logger = object {
        fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
        fun infoText(logText: Any?) = ConsolerizerComposer.out().green(logText)
        fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title(logText))
    }

    private suspend fun runUnconfinedCoroutinesTest(){
        logger.infoText("Unconfined means that the coroutine will execute immediately on the thread giving it priority over child/sub coroutines")
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
        job.join()
    }
}
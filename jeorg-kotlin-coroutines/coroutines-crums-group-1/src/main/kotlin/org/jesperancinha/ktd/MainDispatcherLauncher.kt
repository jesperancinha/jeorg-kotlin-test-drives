package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class MainDispatcherLauncher {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MainDispatcherLauncher::class.java)

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            runMainCoroutinesTest()
        }

        private suspend fun runMainCoroutinesTest() {
            try {
                val job = CoroutineScope(Dispatchers.Main).launch {
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
            } catch (ex: IllegalStateException) {
                logger.info("Error!", ex)
            }
        }

    }
}
package org.jesperancinha.ktd.runs

import kotlinx.coroutines.*
import org.jesperancinha.ktd.Logger
import org.springframework.web.client.RestTemplate
import java.lang.Thread.sleep
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

class NonIOCalls {
    companion object {

        private val logger = Logger
        @OptIn(ExperimentalCoroutinesApi::class)
        fun runIOCoroutinesWithDelay(parallelismOne: Int, parallelismTwo: Int, generateForOne: Int, generateForTwo: Int) = measureTimeMillis {
            runBlocking {
                logger.infoTitle("Testing maximum parallelism with delay")
                logger.info("First test how many threads can we create")

                val dispatcherOne = Dispatchers.IO.limitedParallelism(parallelismOne)
                val dispatcherTwo = Dispatchers.IO.limitedParallelism(parallelismTwo)

                logger.info(Thread.getAllStackTraces().map { it.key.name })
                logger.info("Testing parallelism 5")
                withContext(dispatcherOne) {
                    (0..generateForOne).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            RestTemplate()
                            delay(1000)
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
                logger.info("Testing parallelism 10")
                withContext(dispatcherTwo) {
                    (0..generateForTwo).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            delay(1000)
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
            }
            logger.info(Thread.getAllStackTraces().map { it.key.name })
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        fun runIOCoroutinesWithThread(
            parallelismOne: Int,
            parallelismTwo: Int,
            generateForOne: Int,
            generateForTwo: Int
        ) = measureTimeMillis {
            runBlocking {
                logger.infoTitle("Testing maximum parallelism with sleep")
                logger.info("First test how many threads can we create")

                val dispatcherOne = Dispatchers.IO.limitedParallelism(parallelismOne)
                val dispatcherTwo = Dispatchers.IO.limitedParallelism(parallelismTwo)

                logger.info(Thread.getAllStackTraces().map { it.key.name })
                logger.info("Testing parallelism 5")
                withContext(dispatcherOne) {
                    (0..generateForOne).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            sleep(1000)
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
                logger.info("Testing parallelism 10")
                withContext(dispatcherTwo) {
                    (0..generateForTwo).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            sleep(1000)
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
            }
            logger.info(Thread.getAllStackTraces().map { it.key.name })
        }
    }
}
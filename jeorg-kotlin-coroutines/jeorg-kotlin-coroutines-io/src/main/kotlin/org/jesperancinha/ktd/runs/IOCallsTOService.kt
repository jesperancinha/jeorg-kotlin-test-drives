package org.jesperancinha.ktd.runs

import kotlinx.coroutines.*
import org.jesperancinha.ktd.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis

class IOCallsTOService {
    companion object {

        private val logger = Logger

        @OptIn(ExperimentalCoroutinesApi::class)
        suspend fun runIOCoroutinesWithIOCalls(
            parallelismOne: Int,
            parallelismTwo: Int,
            generateForOne: Int,
            generateForTwo: Int
        ) = measureTimeMillis {
            runBlocking {
                logger.infoTitle("Testing maximum parallelism")
                logger.info("First test how many threads can we create")

                val dispatcherOne = Dispatchers.IO.limitedParallelism(parallelismOne)
                val dispatcherTwo = Dispatchers.IO.limitedParallelism(parallelismTwo)

                logger.info(Thread.getAllStackTraces().map { it.key.name })
                logger.info("Testing parallelism 5")
                withContext(dispatcherOne) {
                    (0..generateForOne).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            val restTemplate = RestTemplate()
                            if (restTemplate.getForEntity("http://localhost:8080/test", Unit::class.java)
                                    .statusCode != HttpStatus.OK
                            ) {
                                throw RuntimeException()
                            }
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
                logger.info("Testing parallelism 10")
                withContext(dispatcherTwo) {
                    (0..generateForTwo).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            val restTemplate = RestTemplate()
                            if (restTemplate.getForEntity("http://localhost:8080/test", Unit::class.java)
                                    .statusCode != HttpStatus.OK
                            ) {
                                throw RuntimeException()
                            }
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
            }
            logger.info(Thread.getAllStackTraces().map { it.key.name })

        }
    }
}
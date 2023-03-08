package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import kotlin.system.exitProcess

@SpringBootApplication
open class IOCoroutineLauncher {
    companion object {
        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().yellow(logText)
            fun infoBefore(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoAfter(logText: Any?) = ConsolerizerComposer.out().red(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            runAllTests()
            exitProcess(0)
        }

        suspend fun runAllTests() {
            SpringApplication.run(IOCoroutineLauncher::class.java)
            logger.infoTitle(
                "Unconfined Coroutines scope tests / There are currently ${
                    Runtime.getRuntime().availableProcessors()
                } CPU's available"
            )
            runIOCoroutinesWithIOCalls()
            runIOCoroutinesWithoutIOCalls()
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        private suspend fun runIOCoroutinesWithIOCalls() {
           runBlocking {
                logger.infoTitle("Testing maximum parallelism")
                logger.info("First test how many threads can we create")

                val dispatcherOne = Dispatchers.IO.limitedParallelism(5)
                val dispatcherTwo = Dispatchers.IO.limitedParallelism(10)

                logger.info(Thread.getAllStackTraces().map { it.key.name })
                logger.info("Testing parallelism 5")
                withContext(dispatcherOne) {
                    (0..10).map {
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
                    (0..10).map {
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

        private fun runIOCoroutinesWithoutIOCalls() {
            runBlocking {
                logger.infoTitle("Testing maximum parallelism")
                logger.info("First test how many threads can we create")

                val dispatcherOne = Dispatchers.IO.limitedParallelism(5)
                val dispatcherTwo = Dispatchers.IO.limitedParallelism(10)

                logger.info(Thread.getAllStackTraces().map { it.key.name })
                logger.info("Testing parallelism 5")
                withContext(dispatcherOne) {
                    (0..10).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            val restTemplate = RestTemplate()
                            delay(1000)
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
                logger.info("Testing parallelism 10")
                withContext(dispatcherTwo) {
                    (0..10).map {
                        launch {
                            logger.infoBefore("Making call $it at ${LocalDateTime.now()}")
                            delay(1000)
                            logger.infoAfter("Finishing call $it at ${LocalDateTime.now()}")
                        }
                    }
                }
            }
            logger.info(Thread.getAllStackTraces().map { it.key.name })        }
    }
}
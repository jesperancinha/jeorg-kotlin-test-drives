package org.jesperancinha.ktd

import kotlinx.coroutines.runBlocking
import org.jesperancinha.ktd.runs.IOCallsTOService
import org.jesperancinha.ktd.runs.NonIOCalls
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.system.exitProcess

@SpringBootApplication
open class IOCoroutineLauncher {
    companion object {
        private val logger = Logger

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
            logger.infoTitleMeasurement("Runtime of runIOCoroutinesWithIOCalls took ${IOCallsTOService.runIOCoroutinesWithIOCalls()} ms")
            logger.infoTitleMeasurement(
                "Runtime of runIOCoroutinesWithDelay took ${
                    NonIOCalls.runIOCoroutinesWithDelay(
                        parallelismOne = 5,
                        parallelismTwo = 10,
                        generateForOne = 10,
                        generateForTwo = 15
                    )
                } ms"
            )
            logger.infoTitleMeasurement(
                "Runtime of runIOCoroutinesWithThread took ${
                    NonIOCalls.runIOCoroutinesWithThread(
                        parallelismOne = 5,
                        parallelismTwo = 10,
                        generateForOne = 10,
                        generateForTwo = 15
                    )
                } ms"
            )
            logger.infoTitleMeasurement(
                "Runtime of BIG runIOCoroutinesWithDelay took ${
                    NonIOCalls.runIOCoroutinesWithDelay(
                        parallelismOne = 100,
                        parallelismTwo = 150,
                        generateForOne = 150,
                        generateForTwo = 200
                    )
                } ms"
            )
            logger.infoTitleMeasurement(
                "Runtime of BIG runIOCoroutinesWithThread took ${
                    NonIOCalls.runIOCoroutinesWithThread(
                        parallelismOne = 100,
                        parallelismTwo = 150,
                        generateForOne = 150,
                        generateForTwo = 200
                    )
                } ms"
            )
        }
    }
}
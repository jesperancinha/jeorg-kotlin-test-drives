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
            val infoTitle = logger.infoTitle(
                "Unconfined Coroutines scope tests / There are currently ${
                    Runtime.getRuntime().availableProcessors()
                } CPU's available"
            )
            val runIOCoroutinesWithIOCalls = IOCallsTOService.runIOCoroutinesWithIOCalls(
                parallelismOne = 5,
                parallelismTwo = 10,
                generateForOne = 10,
                generateForTwo = 15
            )
            val runIOCoroutinesWithDelayMs = NonIOCalls.runIOCoroutinesWithDelay(
                parallelismOne = 5,
                parallelismTwo = 10,
                generateForOne = 10,
                generateForTwo = 15
            )
            val runIOCoroutinesWithThreadMs = NonIOCalls.runIOCoroutinesWithThread(
                parallelismOne = 5,
                parallelismTwo = 10,
                generateForOne = 10,
                generateForTwo = 15
            )
            val runIOCoroutinesWithDelayBigMs = NonIOCalls.runIOCoroutinesWithDelay(
                parallelismOne = 100,
                parallelismTwo = 150,
                generateForOne = 150,
                generateForTwo = 200
            )
            val runIOCoroutinesWithThreadBigMs = NonIOCalls.runIOCoroutinesWithThread(
                parallelismOne = 100,
                parallelismTwo = 150,
                generateForOne = 150,
                generateForTwo = 200
            )
            val runIOCoroutinesWithDelayMegaMs = NonIOCalls.runIOCoroutinesWithDelay(
                parallelismOne = 1000,
                parallelismTwo = 1500,
                generateForOne = 1500,
                generateForTwo = 2000
            )
            val runIOCoroutinesWithThreadMegaMs = NonIOCalls.runIOCoroutinesWithThread(
                parallelismOne = 1000,
                parallelismTwo = 1500,
                generateForOne = 1500,
                generateForTwo = 2000
            )
            logger.infoTitleMeasurement("Runtime of runIOCoroutinesWithIOCalls took $runIOCoroutinesWithIOCalls ms")
            logger.infoTitleMeasurement("Runtime of runIOCoroutinesWithDelay took $runIOCoroutinesWithDelayMs ms")
            logger.infoTitleMeasurement("Runtime of runIOCoroutinesWithThread took $runIOCoroutinesWithThreadMs ms")
            logger.infoTitleMeasurement("Runtime of BIG runIOCoroutinesWithDelay took $runIOCoroutinesWithDelayBigMs ms")
            logger.infoTitleMeasurement("Runtime of BIG runIOCoroutinesWithThread took $runIOCoroutinesWithThreadBigMs ms")
            logger.infoTitleMeasurement("Runtime of MEGA runIOCoroutinesWithDelay took $runIOCoroutinesWithDelayMegaMs ms")
            logger.infoTitleMeasurement("Runtime of MEGA runIOCoroutinesWithThread took $runIOCoroutinesWithThreadMegaMs ms")
        }
    }
}
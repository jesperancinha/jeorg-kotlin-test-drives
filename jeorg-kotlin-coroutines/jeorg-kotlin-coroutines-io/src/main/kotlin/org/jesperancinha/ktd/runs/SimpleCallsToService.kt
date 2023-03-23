package org.jesperancinha.ktd.runs

import kotlinx.coroutines.*
import org.jesperancinha.ktd.Logger
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

class SimpleCallsToService {
    companion object {

        private val logger = Logger

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            runBlocking {
                logger.infoBefore("Making call at ${LocalDateTime.now()}")
                val atomicInteger = AtomicInteger()
                val duration = measureTimeMillis {
                    withContext(Dispatchers.IO.limitedParallelism(100)) {
                        repeat(100) {
                            launch {
                                delay(1000)
                                atomicInteger.addAndGet(1)
                            }
                        }
                    }
                }
                logger.infoAfter("Finishing call at ${LocalDateTime.now()}")
                logger.info("It took $duration milliseconds long")
                logger.info("Caught $atomicInteger ending coroutines")
            }
            exitProcess(0)

        }
    }
}
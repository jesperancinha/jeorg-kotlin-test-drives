package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class UniversalLauncher {

    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.outSpace().green(logText.toString())
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = run {
            runBlocking {
                coroutineScope {
                    withContext(Dispatchers.IO) {
                        thread {
                            logger.info("I am a thread!")
                            throw RuntimeException("This will not stop the program")
                        }.join()
                    }
                    measureTimeMillis {
                        async {
                            logger.info("I'm a coroutine running asynchronously and I am not nested")
                            delay(1000)
                            logger.info("Now I'm done!")
                        }.join()
                        async {
                            logger.info("I'm a coroutine running asynchronously and I am not nested")
                            delay(1000)
                            logger.info("Now I'm done!")
                        }.join()
                    }.let { logger.info("The first two asyncs with 1s delay took 2 seconds to run with join(): $it ms") }
                    measureTimeMillis {
                        async {
                            logger.info("I'm a coroutine running asynchronously and I am not nested")
                            delay(1000)
                            logger.info("Now I'm done!")
                        }.await()
                        async {
                            logger.info("I'm a coroutine running asynchronously and I am not nested")
                            delay(1000)
                            logger.info("Now I'm done!")
                        }.await()
                    }.let { logger.info("The first two asyncs with 1s delay took 1 seconds to run with await(): $it ms") }
                    launch {
                        logger.info("I'm a coroutine being launched as a nested coroutine")
                    }.join()

                }
            }
            logger.info("Main!")
            Unit
        }
    }
}
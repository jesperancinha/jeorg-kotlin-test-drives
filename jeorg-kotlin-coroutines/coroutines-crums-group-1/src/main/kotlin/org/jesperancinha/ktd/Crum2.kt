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
            makeThreadTest()
            makeLaunchTest()
            makeAsyncTest()
            makeGenericTest()
            logger.info("Main!")
            Unit
        }

        private fun makeGenericTest() {
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
                        launch {
                            logger.info("I'm a coroutine being launched as a nested coroutine")
                        }.join()
                    }.let { logger.info("The first two asyncs with 1s delay took 1 seconds to run with await(): $it ms") }
                }
            }
        }

        private fun makeAsyncTest() {
            runBlocking {
                measureTimeMillis {
                    coroutineScope {
                        listOf(
                            async {
                                logger.info("I'm a coroutine running with async and I am not nested")
                                delay(1000)
                                logger.info("Now I'm done!")
                            },
                            async {
                                logger.info("I'm a coroutine running with async and I am not nested")
                                delay(1000)
                                logger.info("Now I'm done!")
                            }).awaitAll()
                    }
                }.let { logger.info("The two asyncs with 1s delay took 1 seconds to run with await(): $it ms") }
            }
        }

        private fun makeLaunchTest() {
            runBlocking {
                measureTimeMillis {
                    coroutineScope {
                        launch {
                            logger.info("I'm a coroutine running with launch and I am not nested")
                            delay(1000)
                            logger.info("Now I'm done!")
                        }
                        launch {
                            logger.info("I'm a coroutine running with launch and I am not nested")
                            delay(1000)
                            logger.info("Now I'm done!")
                        }
                    }
                }.let { logger.info("The two launches with 1s delay took 1 seconds to run: $it ms") }
            }
        }

        private fun makeThreadTest() {
            runBlocking {
                coroutineScope {
                    withContext(Dispatchers.IO) {
                        thread {
                            logger.info("I am a thread!")
                            throw RuntimeException("This will not stop the program")
                        }.join()
                    }
                }
            }

        }
    }
}
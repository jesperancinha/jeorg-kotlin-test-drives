package org.jesperancinha.ktd

import kotlinx.coroutines.*

object NeuronCancellationException : CancellationException() {
    private fun readResolve(): Any = NeuronCancellationException
}

object NetworkCancellationException : Exception() {
    private fun readResolve(): Any = NetworkCancellationException
}


class NeuralNetworksCancelling {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            CoroutineScope(Dispatchers.Default).launch {
                (1..10).forEach { rank ->
                    launch {
                        launch {
                            launch {
                                println("NeuronCancellationException - Only this one will be cancelled - Rank:$rank")
                                throw NeuronCancellationException
                            }
                            delay(50)
                            println("Prints Level 3 - Rank:$rank")
                        }
                        delay(50)
                        println("Prints Level 2 - Rank:$rank")
                    }
                    delay(50)
                    println("Prints Level 1 - Rank:$rank")
                }
            }.join()
            CoroutineScope(Dispatchers.Default).launch {
                (1..10).forEach { rank ->
                    launch {
                        launch {
                            launch {
                                println("NetworkCancellationException - Only this one will be cancelled - Rank:$rank")
                                throw NetworkCancellationException
                            }
                            delay(50)
                            println("Prints Level 3 - Rank:$rank")
                        }
                        delay(50)
                        println("Prints Level 2 - Rank:$rank")
                    }
                    delay(50)
                    println("Prints Level 1 - Rank:$rank")
                }
            }.join()
            println("Finished CancellationExceptionTests")

        }
    }
}
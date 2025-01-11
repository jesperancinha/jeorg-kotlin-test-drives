package org.jesperancinha.ktd

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ActorsAndProducers {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            producerExample()
            actorExample()
        }

        @OptIn(ObsoleteCoroutinesApi::class)
        private fun actorExample()= runBlocking {
            val counter = actor<Int> {
                var sum = 0
                for (value in channel) {
                    sum += value
                    println("Current sum: $sum")
                }
            }

            counter.send(10)
            counter.send(20)
            counter.send(30)

            delay(500)
            counter.close()
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        private fun producerExample()= runBlocking {
            val channel = produce {
                for (i in 1..5) {
                    delay(100)
                    send(i)
                }
            }

            for (item in channel) {
                println("Received: $item")
            }
        }
    }
}
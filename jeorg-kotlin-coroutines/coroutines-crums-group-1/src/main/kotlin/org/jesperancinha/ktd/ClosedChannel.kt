package org.jesperancinha.ktd

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

class ClosedChannel {
    companion object {

        private val channel by lazy { Channel<String>(5) }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            channel.close()
            runCatching {
                channel.send("A")
            }.onFailure { println("Error(${it.javaClass}): ${it.message}") }
        }
    }
}
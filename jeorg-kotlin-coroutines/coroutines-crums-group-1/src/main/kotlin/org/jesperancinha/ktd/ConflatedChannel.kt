package org.jesperancinha.ktd

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConflatedChannel {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            val conflatedChannel = Channel<Int>(Channel.CONFLATED)

            launch {
                repeat(5) { value ->
                    println("Sending: $value")
                    conflatedChannel.send(value)
                    delay(500)
                }
                conflatedChannel.close()
            }

            launch {
                conflatedChannel.receiveAsFlow().collect { value ->
                    println("Received: $value")
                    delay(1000)
                }
            }
        }
    }
}
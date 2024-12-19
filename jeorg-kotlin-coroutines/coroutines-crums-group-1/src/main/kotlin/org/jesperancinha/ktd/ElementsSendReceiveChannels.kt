package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds


private suspend fun fetchItem() = withContext(Dispatchers.Default) { "ITEM-${UUID.randomUUID()}" }
private const val PRODUCTION_COST_MS = 200L

class ElementsSendReceiveBlockingChannel {

    private val linkedBlockingQueue by lazy { LinkedBlockingQueue<String>(100) }
    private suspend fun produceItems() {
        val item = fetchItem()
        delay(PRODUCTION_COST_MS)
        linkedBlockingQueue.add(item)
        delay(PRODUCTION_COST_MS)
        linkedBlockingQueue.add(item)
    }

    private suspend fun receiveItems() {
        do {
            val item = withContext(Dispatchers.IO) {
                linkedBlockingQueue.take()
            }
            println("LinkedBlockingQueue received item $item!")
        } while (!linkedBlockingQueue.isEmpty())
    }

    suspend fun doMain() = coroutineScope {
        val receiveTask = async { receiveItems() }
        val produceTask = async { produceItems() }
        produceTask.await()
        receiveTask.await()
    }
}

class ElementsSendReceiveChannel {

    private val channel by lazy { Channel<String>(5) }

    private suspend fun produceItems() {
        repeat(5) {
            val item = fetchItem()
            delay(PRODUCTION_COST_MS)
            channel.send(item)
        }
    }


    private suspend fun receiveItems() {
        repeat(5) {
            val item = channel.receive()
            delay(PRODUCTION_COST_MS)
            println("Channel received item $item!")
        }
    }

    suspend fun doMain() = coroutineScope {
        measureTimeMillis {
            launch { produceItems() }.join()
            launch { receiveItems() }.join()
        }.run { println("It too $this ms to complete!") }
    }
}

class ElementsSendReceiveRendezVousChannel {

    private val channel by lazy { Channel<String>() }

    private suspend fun produceItems() {
        repeat(5) {
            val item = fetchItem()
            delay(PRODUCTION_COST_MS)
            channel.send(item)
        }
    }


    private suspend fun receiveItems() {
        repeat(5) {
            val item = channel.receive()
            delay(PRODUCTION_COST_MS)
            println("Channel received item $item!")
        }
    }

    suspend fun doMain() = coroutineScope {
        runCatching {
            withTimeout(5.seconds) {
                measureTimeMillis {
                    launch { produceItems() }.join()
                    launch { receiveItems() }.join()
                }.run { println("It too $this ms to complete!") }
            }
        }.onFailure {
            println("Rendez-vous channels, or channels without a buffer, will fail in this case because they will suspend on send if there is still an element to be received by the other coroutine.")
        }
    }
}

class ElementsSendReceiveRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            ElementsSendReceiveBlockingChannel().doMain()
            ElementsSendReceiveChannel().doMain()
            ElementsSendReceiveRendezVousChannel().doMain()
        }
    }
}
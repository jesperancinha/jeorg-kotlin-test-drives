package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.*
import java.util.concurrent.LinkedBlockingQueue


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

    private val channel by lazy { Channel<String>(2) }

    private suspend fun produceItems() {
        val item = fetchItem()
        delay(PRODUCTION_COST_MS)
        channel.send(item)
        delay(PRODUCTION_COST_MS)
        channel.send(item)
    }


    private suspend fun receiveItems() {
        repeat(2) {
            val item = channel.receive()
            println("Channel received item $item!")
        }
    }

    suspend fun doMain() = coroutineScope {
        launch { produceItems() }
        launch { receiveItems() }
    }
}

class ElementsSendReceiveRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            ElementsSendReceiveBlockingChannel().doMain()
            ElementsSendReceiveChannel().doMain()
        }
    }
}
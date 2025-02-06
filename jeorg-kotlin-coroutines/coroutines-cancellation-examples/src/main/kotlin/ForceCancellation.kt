package org.jesperancinha.ktd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileNotFoundException
import kotlin.random.Random


/**
 * Force cancellation
 * This example is here to show that we CANNOT cancel coroutines immediately
 * @constructor Create empty Force cancellation
 */
object ForceCancellation {
    suspend fun simulateBlockingIOMethod(): String {
        println("Started blocking I/O operation")
        delay(2000)
        if (Random.nextBoolean()) {
            throw FileNotFoundException("File not found!")  // Simulate possible error
        }
        return "I/O Operation Complete!"
    }

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        var coroutineThread:Thread? = null
        val job = launch(Dispatchers.IO) {
            coroutineThread = Thread.currentThread()
            try {
                val result = simulateBlockingIOMethod()
                println(result)
            } catch (e: Exception) {
                println("Exception caught: ${e.message}")
            }
        }
        delay(1000)
        coroutineThread?.interrupt()
        println("Cancelling coroutine...")
        job.cancel()
        delay(3000)
        println("Main function ends")
    }
}
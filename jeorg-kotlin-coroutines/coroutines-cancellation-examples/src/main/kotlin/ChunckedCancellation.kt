package org.jesperancinha.ktd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

/**
 * Chuncked cancellation
 *
 * @constructor Create empty Chuncked cancellation
 */
object ChunckedCancellation {

    suspend fun writeFileInChunks(file: File, content: String) {
        val chunkSize = 1024
        var position = 0
        val length = content.length

        while (position < length && isActive) {
            val chunk = content.substring(position, minOf(position + chunkSize, length))
            file.appendText(chunk)
            position += chunkSize
            delay(50)
        }

        if (!isActive) {
            println("File write cancelled")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {

        val job = launch(Dispatchers.IO) {
            val file = File("example.txt")
            try {
                writeFileInChunks(file, "Some large content that needs to be written in chunks")
            } catch (e: CancellationException) {
                println("File write operation was cancelled")
            }
        }

        delay(1000)
        println("Cancelling coroutine...")
        job.cancel()
        delay(3000)
        println("Main function ends")

    }

}
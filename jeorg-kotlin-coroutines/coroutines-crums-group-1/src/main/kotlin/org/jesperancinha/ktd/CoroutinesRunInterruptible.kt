package org.jesperancinha.ktd

import kotlinx.coroutines.*
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import kotlin.time.Duration.Companion.seconds

object CoroutinesRunInterruptible {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val filePath = Paths.get("largefile.txt")

        // ✅ Step 1: Generate a large file if it doesn't exist
        if (!Files.exists(filePath)) {
            println("Generating large file...")
            Files.newBufferedWriter(filePath, StandardOpenOption.CREATE).use { writer ->
                repeat(1000) { writer.write("Line $it\n") }
            }
            println("Large file created")
        }

        // ✅ Step 2: Launch a coroutine that reads the file using an interruptible method
        val job = launch(Dispatchers.IO) {
            runInterruptible {
                try {
                    println("Starting file read...")
                    Files.newInputStream(filePath).use { inputStream ->
                        val buffer = ByteArray(1024)
                        while (true) {
                            val bytesRead = inputStream.read(buffer) // ⚠️ This is interruptible!
                            if (bytesRead == -1) break
                            println("Read $bytesRead bytes")
                            Thread.sleep(1000) // Simulate processing
                        }
                    }
                    println("File read complete")

                } catch (e: InterruptedException) {
                    println("Blocking operation was interrupted!")
                }
            }
        }

        // ✅ Step 3: Let the coroutine start reading
        delay(2.seconds) // Allow some reading to happen

        // ✅ Step 4: Cancel the job
        println("Cancelling the job...")
        job.cancelAndJoin() // Cancels and interrupts the operation
        println("Job cancelled")
    }

}
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

        if (!Files.exists(filePath)) {
            println("Generating large file...")
            Files.newBufferedWriter(filePath, StandardOpenOption.CREATE).use { writer ->
                repeat(1000) { writer.write("Line $it\n") }
            }
            println("Large file created")
        }

        val job = launch(Dispatchers.IO) {
            runInterruptible {
                try {
                    println("Starting file read...")
                    Files.newInputStream(filePath).use { inputStream ->
                        val buffer = ByteArray(1024)
                        while (true) {
                            val bytesRead = inputStream.read(buffer)
                            if (bytesRead == -1) break
                            println("Read $bytesRead bytes")
                            Thread.sleep(1000)
                        }
                    }
                    println("File read complete")

                } catch (e: InterruptedException) {
                    println("Blocking operation was interrupted!")
                }
            }
        }

        delay(2.seconds)

        println("Cancelling the job...")
        job.cancelAndJoin()
        println("Job cancelled")
    }

}
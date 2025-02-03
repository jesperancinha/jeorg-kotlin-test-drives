package org.jesperancinha.ktd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.File

class IODispatchersJob {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val file1 = "file1.txt"
            val file2 = "file2.txt"

            val job1 = launch(Dispatchers.IO) { writeToFileWithYield(file1, "File 1 - Part 1", "File 1 - Part 2") }
            val job2 = launch(Dispatchers.IO) { writeToFileWithYield(file2, "File 2 - Part 1", "File 2 - Part 2") }

            job1.join()
            job2.join()

            val content1 = readFromFile(file1)
            val content2 = readFromFile(file2)

            println("Final content of $file1:\n$content1")
            println("Final content of $file2:\n$content2")
        }

        suspend fun writeToFileWithYield(fileName: String, part1: String, part2: String) {
            withContext(Dispatchers.IO) {
                File(fileName).writeText(part1)
                println("Wrote first part to $fileName, yielding execution...")
                println("Before current Thread ${Thread.currentThread()} in context ${kotlin.coroutines.coroutineContext}")
                yield()
                println("After current Thread ${Thread.currentThread()} in context ${kotlin.coroutines.coroutineContext}")
                File(fileName).appendText("\n$part2")
                println("Wrote second part to $fileName.")
            }
        }

        suspend fun readFromFile(fileName: String): String {
            return withContext(Dispatchers.IO) {
                File(fileName).readText()
            }
        }
    }
}

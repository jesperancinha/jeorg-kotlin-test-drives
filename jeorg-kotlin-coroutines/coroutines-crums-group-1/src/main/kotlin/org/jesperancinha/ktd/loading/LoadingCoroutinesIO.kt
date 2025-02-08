package org.jesperancinha.ktd.loading

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

object LoadingCoroutinesIO {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val file = File("example.txt").apply { writeText("Kotlin Coroutines Dispatchers.IO") }

        repeat(100) { index ->
            launch(Dispatchers.IO) {
                val threadName = Thread.currentThread().name
                val content = file.readText()
                println("[$index] Read by thread: $threadName -> ${content.take(20)}...")
                delay(100)
            }
        }
    }
}

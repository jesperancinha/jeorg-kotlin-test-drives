package org.jesperancinha.ktd.loading

import kotlinx.coroutines.*
import java.io.File
import java.lang.Thread.sleep
import kotlin.system.measureTimeMillis

object LoadingCoroutinesIO {
    val file = File("example.txt").apply { writeText("Kotlin Coroutines Dispatchers.IO") }
    
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        runTest(10)
        runTest(50)
        runTest(100)
        runTest(200)
        runTest(500)
        runTest(1000)
        runTest(5000)
        runTest(10000)
    }

    private suspend fun CoroutineScope.runTest(max: Int) {
        println("-------------------------")
        println("Launching $max coroutines")
        measureTimeMillis {
            (1..max).map { index ->
                launch(Dispatchers.IO) {
                    file.readText()
                    sleep(10)
                }
            }.joinAll()
            println(
                "${
                    Thread.currentThread().threadGroup.activeCount()
                } threads have been traced."
            )
        }.run { println("Took ${this} ms to complete!") }
    }
}

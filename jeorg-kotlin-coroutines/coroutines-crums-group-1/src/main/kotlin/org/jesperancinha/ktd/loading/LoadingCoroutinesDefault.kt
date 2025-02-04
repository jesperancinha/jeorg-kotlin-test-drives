package org.jesperancinha.ktd.loading

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.sqrt
import kotlin.time.Duration.Companion.seconds

object LoadingCoroutinesDefault {
    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking{
        val cores = Runtime.getRuntime().availableProcessors()
        println("Available cores: $cores")
        repeat(cores * 2) {
            launch(Dispatchers.Default) {
                println("Running on thread: ${Thread.currentThread().name}")
                while (true) {
                    sqrt(Math.random())
                }
            }
        }
        delay(10.seconds)
    }
}
package org.jesperancinha.ktd

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay
import java.time.Duration
import kotlin.random.Random
import kotlin.random.Random.Default.nextLong

class SimpleConcurrency {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking{
            val coroutine1 = async {
                delay(Duration.ofMillis(nextLong(500)))
                println("Coroutine 1 is complete!")
            }
            val coroutine2 = async {
                delay(Duration.ofMillis(nextLong(500)))
                println("Coroutine 2 is complete!")
            }
            coroutine2.await()
            coroutine1.await()
        }
    }
}

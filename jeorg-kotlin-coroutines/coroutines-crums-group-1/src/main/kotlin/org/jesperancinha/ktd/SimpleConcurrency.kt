package org.jesperancinha.ktd

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay
import java.lang.Thread.sleep
import java.time.Duration
import kotlin.random.Random.Default.nextLong
import kotlin.system.measureTimeMillis

class SimpleConcurrency {
    companion object {
        /**
         * This test runs sleep with the purpose to show concurrency in coroutines
         */
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val timeToRun = measureTimeMillis {
                val coroutine1 = async {
                    delay(Duration.ofMillis(nextLong(100)))
                    async {
                        val randomTime = nextLong(500)
                        sleep(Duration.ofMillis(randomTime))
                        println("Coroutine 1 is complete in $randomTime!")
                    }.await()
                }
                val coroutine2 =
                    async {
                        delay(Duration.ofMillis(nextLong(100)))
                        async {
                            val randomTime = nextLong(500)
                            sleep(Duration.ofMillis(randomTime))
                            println("Coroutine 2 is complete in $randomTime!")
                        }.await()
                    }
                coroutine2.await()
                coroutine1.await()
            }
            println("Time to run is $timeToRun milliseconds")
        }
    }
}

package org.jesperancinha.ktd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

class DispatchersDefaultJob {
    companion object {

        suspend fun findPrimesInRange(range: IntRange): List<Int> {
            return range.filter { n ->
                yield()
                println("Current Thread ${Thread.currentThread()} in context ${coroutineContext}")
                isPrime(n)
            }
        }

        fun isPrime(number: Int): Boolean {
            if (number <= 1) return false
            for (i in 2 until number) {
                if (number % i == 0) {
                    return false
                }
            }
            return true
        }
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking(Dispatchers.Default) {
            val range1 = 1_000_000..1_000_050
            val range2 = 1_000_051..1_000_100

            val time = measureTimeMillis {
                val deferred1 = async { findPrimesInRange(range1) }
                val deferred2 = async { findPrimesInRange(range2) }

                val primes = deferred1.await() + deferred2.await()
                println("Found ${primes.size} prime numbers")
            }

            println("Execution time: $time ms")
        }

    }
}
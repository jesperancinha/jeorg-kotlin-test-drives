package org.jesperancinha.ktd

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime

class YieldingTest {
    @Test
    fun `should timeout after 10 seconds`() {
        measureTime {
            runCatching {
                runTest {
                    println(backgroundScope)
                    val job = backgroundScope.launch {
                        while (isActive) {
                            yield()
                        }
                    }
                    delay(1)
                    job.shouldNotBeNull()
                }
            }
        }.shouldBeGreaterThan(10.seconds)
    }

    @Test
    fun `should cancel with runBlocking`(): Unit = runBlocking {
        println(this)
        val job = this.launch {
            while (isActive) {
                yield()
            }
        }
        delay(1)
        job.cancelAndJoin()
        job.isCancelled.shouldBeTrue()
    }

    @Test
    fun `should cancel with runTest`() = runTest {
        val job = backgroundScope.launch {
            while (isActive) {
                delay(1)
            }
        }
        delay(10)
        job.cancelAndJoin()
        job.isCancelled.shouldBeTrue()
    }
}
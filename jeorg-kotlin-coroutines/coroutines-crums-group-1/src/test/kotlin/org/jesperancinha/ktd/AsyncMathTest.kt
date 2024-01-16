package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.test.Test

class AsyncMathTest {
    @Test
    fun `should find coordinates synchronously in spite of async`() {
        runBlocking {
            val x = async {
                findX()
            }
            val y = async {
                findY()
            }
            val z = async {
                findZ()
            }
            showCoordinates(x, y, z)
            println(coroutineContext)
        }
    }

    @Test
    fun `should find coordinates asynchronously because of non-blocking with explicit Dispatchers_IO`(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            val x = async {
                findX()
            }
            val y = async {
                findY()
            }
            val z = async {
                findZ()
            }
            showCoordinates(x, y, z)
            println(coroutineContext)
        }
    }

    @Test
    fun `should find coordinates asynchronously because of non-blocking  with coroutineScope`(): Unit = runBlocking {
        coroutineScope {
            CoroutineScope(Dispatchers.Default).launch {
                val x = async {
                    findX()
                }
                val y = async {
                    findY()
                }
                val z = async {
                    findZ()
                }
                showCoordinates(x, y, z)
                println(coroutineContext)
            }.join()
        }

    }

    private fun findZ(): Long {
        println("z")
        return Random.nextLong(1000)
    }

    private fun findY(): Long {
        println("y")
        return Random.nextLong(1000)

    }

    private fun findX(): Long {
        print(".")
        repeat(1000) {
            print(".")
        }
        println()
        println("x")
        return Random.nextLong(1000)
    }

    private suspend fun showCoordinates(
        x: Deferred<Long>,
        y: Deferred<Long>,
        z: Deferred<Long>
    ) {
        println("The coordinates are (${x.await()}, ${y.await()}, ${z.await()}) ")
    }

}
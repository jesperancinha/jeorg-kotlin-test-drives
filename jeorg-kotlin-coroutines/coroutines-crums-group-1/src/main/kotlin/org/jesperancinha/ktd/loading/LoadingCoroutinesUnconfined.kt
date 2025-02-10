package org.jesperancinha.ktd.loading

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object LoadingCoroutinesUnconfined {

    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        println("Starting coroutine unconfined - ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
        launch(Dispatchers.Unconfined) {
            println("1 - Start in thread: ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
            delay(1000)
            println("1 - Resumed in thread: ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
            delay(1000)
            println("1 - Resumed in thread: ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
        }

        launch(Dispatchers.Unconfined) {
            println("2 - Start in thread: ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
            delay(1000)
            println("2 - Resumed in thread: ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
            delay(1000)
            println("2 - Resumed in thread: ${Thread.currentThread().name} with coroutine context ${currentCoroutineContext()}")
        }
    }
}
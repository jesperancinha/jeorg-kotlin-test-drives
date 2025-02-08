package org.jesperancinha.ktd.loading

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object LoadingCoroutinesUnconfined {

    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        launch(Dispatchers.Unconfined) {
            println("Start in thread: ${Thread.currentThread().name}")
            delay(1000)
            println("Resumed in thread: ${Thread.currentThread().name}")
        }
    }
}
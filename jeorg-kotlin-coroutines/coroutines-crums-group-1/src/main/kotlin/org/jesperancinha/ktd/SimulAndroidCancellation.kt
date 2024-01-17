package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class SimulAndroidCancellation {
    companion object {
        private val viewModelScope by lazy { CoroutineScope(Dispatchers.Unconfined) }
        init {
            viewModelScope.launch {
                connect()
            }
        }

        private fun onCleared() {
            viewModelScope.cancel()
        }

        lateinit var connectionScope: CoroutineScope
        suspend fun connect() = coroutineScope {
            connectionScope = this
            try {
                println("Started connector simulation")
                delay(1000)
                println("Ended connector simulation")
            } catch (_: Exception) {
            }
            this
        }

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            measureTimeMillis {
                onCleared()
            }.let { println("Process took $it milliseconds") }
            delay(2000)
            println("We should not see the \"End connector...\" message in the end.")
        }
    }
}
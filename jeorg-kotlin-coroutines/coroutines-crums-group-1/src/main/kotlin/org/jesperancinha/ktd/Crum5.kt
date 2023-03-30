package org.jesperancinha.ktd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NeuralNetworksCancelling {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = run {
            CoroutineScope(Dispatchers.Default).launch {
                (1..100).forEach {
                    launch {
                        launch {
                            launch { }
                        }
                    }
                }
            }
        }
    }
}
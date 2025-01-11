package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

class StateSharedFlow {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runCatching {
                stateFlowExample()
            }
            println("******")
            runCatching {
                sharedFlowExample()
            }
        }

        private fun sharedFlowExample() = runBlocking {
            withTimeout(10.seconds) {
                val sharedFlow = MutableSharedFlow<Int>(replay = 2)

                launch {
                    sharedFlow.collect { value ->
                        println("Collector 1: SharedFlow value = $value")
                    }
                }

                launch {
                    sharedFlow.collect { value ->
                        println("Collector 2: SharedFlow value = $value")
                    }
                }

                delay(1000)
                sharedFlow.emit(1)

                delay(1000)
                sharedFlow.emit(2)

                delay(1000)
                sharedFlow.emit(3)

                delay(2000)
            }
        }

        private fun stateFlowExample() = runBlocking {
            withTimeout(10.seconds) {
                val stateFlow = MutableStateFlow(0)

                launch {
                    stateFlow.collect { value ->
                        println("Collector 1: StateFlow value = $value")
                    }
                }

                launch {
                    stateFlow.collect { value ->
                        println("Collector 2: StateFlow value = $value")
                    }
                }

                delay(1000)
                stateFlow.value = 1

                delay(1000)
                stateFlow.value = 2

                delay(2000)
            }
        }
    }
}

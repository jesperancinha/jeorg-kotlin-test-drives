package org.jesperancinha.ktd.crums3.mutablevssharedflows

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class MutableSharedFlowCheck {

    companion object{
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) =  runBlocking {

            val stateFlow = MutableStateFlow(0)
            stateFlow.value = 1

            val stateFlowJob = launch {
                stateFlow.collect { value ->
                    println("MutableStateFlow Collected value: $value  at ${LocalDateTime.now()}")
                }
            }

            repeat(5) {
                delay(100)
                stateFlow.value = it + 1
                stateFlow.value = it + 1
            }

            val sharedFlow = MutableSharedFlow<Int>(replay = 4)
            sharedFlow.emit(1)

            val sharedFlowJob = launch {
                sharedFlow.collect { value ->
                    println("MutableSharedFlow Collected value: $value  at ${LocalDateTime.now()}")
                }
            }

            repeat(5) {
                delay(100)
                sharedFlow.emit(it + 1)
            }

            val sharedFlowJob2 = launch {
                sharedFlow.collect { value ->
                    println("MutableSharedFlow2 Collected value: $value  at ${LocalDateTime.now()}")
                }
            }

            delay(1000)
            sharedFlowJob.cancel()
            sharedFlowJob2.cancel()
            stateFlowJob.cancel()
            cancel()
        }
    }
}
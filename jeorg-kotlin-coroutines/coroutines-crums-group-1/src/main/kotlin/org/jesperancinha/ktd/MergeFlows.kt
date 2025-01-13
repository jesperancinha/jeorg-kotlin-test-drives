package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking

class MergeFlows {
    companion object{
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
                val flow1 = flow {
                    delay(100)
                    emit(1)
                    delay(100)
                    emit(2)
                }

                val flow2 = flow {
                    delay(50)
                    emit("A")
                    delay(150)
                    emit("B")
                }

                merge(flow1, flow2).collect { value ->
                    println(value)
            }

        }
    }
}
package org.jesperancinha.ktd

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class UncollectedFlows {
    companion object {
        private fun simpleFlow(): Flow<Int> = flow {
            println("Flow started")
            for (i in 1..3) {
                delay(1000)
                println("Emitting $i")
                emit(i)
            }
        }
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
                val launch = async {
                    simpleFlow()
                }
            println("Flow was launched but not collected")
                delay(2000)

            launch.await().collect {
                println("Now it is collected $it")
            }
        }
    }
}
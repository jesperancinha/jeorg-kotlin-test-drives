package org.jesperancinha.ktd

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class FlowExceptions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val flow = flow {
                emit(1)
                emit(2)
                throw RuntimeException("Something went wrong!")
            }

            flow
                .catch { e ->
                    println("Caught exception: ${e.message}")
                    emit(-1)
                }
                .collect { value ->
                    println("Collected: $value")
                }
        }
    }
}
package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class ConflatedFlow {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            flow {
                repeat(5) {
                    emit(it)
                    println("Emitting $it")
                    delay(100)
                }
            }
                .conflate()
                .collect { value ->
                    println("Collecting $value")
                    delay(400)
                }
        }
    }
}
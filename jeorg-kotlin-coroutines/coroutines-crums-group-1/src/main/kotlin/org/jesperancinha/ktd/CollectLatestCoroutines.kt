package org.jesperancinha.ktd

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class CollectLatestCoroutines {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val flow = flow {
                emit(1)
                delay(100)
                emit(2)
            }

            flow.collectLatest { value ->
                println("Collecting $value")
                delay(200)
                println("Done processing $value")
            }
        }
    }
}
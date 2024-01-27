package org.jesperancinha.ktd

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class RunningFold {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val initial: Pair<String?, String>? = null
            val allPairs = flow {
                emit("Oyster")
                emit("Clam")
                emit("Crab")
            }.runningFold(initial) { lastPair, next ->
                lastPair?.run {
                    val (_, last) = this
                    last to next
                } ?: (null to next)

            }.filterNotNull()
            allPairs.collect {
                println(it)
            }


            tryFold()
        }

        private suspend fun tryFold() {
            val initial: Pair<String?, String>? = null
            val pair = flow {
                emit("Oyster")
                emit("Clam")
                emit("Crab")
            }.fold(initial) { lastPair, next ->
                lastPair?.run {
                    val (_, last) = this
                    last to next
                } ?: (null to next)

            }
            println(pair)
        }
    }
}
package org.jesperancinha.ktd

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking

class FlatMapConcat {
    companion object {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): kotlin.Unit = runBlocking {
            val flow1 = flow {
                emit("First flow: A")
                kotlinx.coroutines.delay(100)
                emit("First flow: B")
            }
            val flow2 = flow {
                emit("Second flow: X")
                kotlinx.coroutines.delay(100)
                emit("Second flow: Y")
            }
            val flowOfFlows = flowOf(
                flow1,
                flow2
            )

            flowOfFlows.flatMapConcat { innerFlow ->
                innerFlow
            }.collect { value ->
                println(value)
            }

            testWithMerge()
        }

        private fun testWithMerge() = runBlocking {
            val flow1 = flow {
                emit("First flow: A")
                kotlinx.coroutines.delay(100)
                emit("First flow: B")
            }
            val flow2 = flow {
                emit("Second flow: X")
                kotlinx.coroutines.delay(100)
                emit("Second flow: Y")
            }
            merge(flow1, flow2)
                .collect {
                    println(it)
                }
        }
    }
}
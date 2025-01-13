package org.jesperancinha.ktd

import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

class ZipVsCombine {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): kotlin.Unit = runBlocking {
            combineExample()
            println("**************")
            zipExample()
        }

        private suspend fun zipExample() {
            val mainBodyFlow = flow {
                emit("Body1")
                delay(100) 
                emit("Body2")
            }

            val wheelsFlow = flow {
                emit(listOf("Wheel1", "Wheel2", "Wheel3", "Wheel4"))
                delay(200)
                emit(listOf("Wheel5", "Wheel6", "Wheel7", "Wheel8"))
            }

            mainBodyFlow.zip(wheelsFlow) { body, wheels ->
                "Robot: $body with wheels $wheels"
            }.collect { robot ->
                println("Registered: $robot")
            }
        }

        private suspend fun combineExample() {
            val mainBodyFlow = flow {
                emit("Body1")
                delay(100) 
                emit("Body2")
            }

            val wheelsFlow = flow {
                emit(listOf("Wheel1", "Wheel2", "Wheel3", "Wheel4"))
                delay(200) 
                emit(listOf("Wheel5", "Wheel6", "Wheel7", "Wheel8"))
            }

            combine(mainBodyFlow, wheelsFlow) { body, wheels ->
                "Robot: $body with wheels $wheels"
            }.collect { robot ->
                println("Registered: $robot")
            }
        }
    }
}
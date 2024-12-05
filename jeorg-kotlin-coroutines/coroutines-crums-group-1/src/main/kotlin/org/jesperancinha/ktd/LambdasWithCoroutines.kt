package org.jesperancinha.ktd

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class LambdasWithCoroutines {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            async { execute { echoHelloWorld() } }.await()
            async { executeSuspended { suspendEchoHelloWorld() } }.await()
        }

        private suspend fun suspendEchoHelloWorld() = coroutineScope { println("suspendEchoHelloWorld") }
        private fun echoHelloWorld() = println("echoHelloWorld")

        private suspend fun <T> executeSuspended(action: suspend () -> T): T {
            return action()
        }

        private suspend fun <T> execute(action: () -> T): T {
            return coroutineScope { action() }
        }
    }
}
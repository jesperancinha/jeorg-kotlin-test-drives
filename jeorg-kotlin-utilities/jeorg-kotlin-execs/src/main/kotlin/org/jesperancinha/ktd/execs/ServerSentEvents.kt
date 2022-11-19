package org.jesperancinha.ktd.execs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

const val sentence = "In the garden of Eden"

/**
 * SSE
 */
fun randomWordFlow(): Flow<String> = flow {
    val words = sentence.split(" ")
    repeat(1000) {
        emit(words.random())
    }
}

fun callFlow() = runBlocking {
    randomWordFlow().collect { word -> print(" $word") }
}

fun main() {
    callFlow()
}


package org.jesperancinha.ktd.execs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

private const val sentence = "Who is awesome? You are!"


val counter = AtomicInteger(0)

/**
 * SSE
 */
fun randomWordFlow(): Flow<String> = flow {
    val words = sentence.split(" ")
    repeat(5) {
        emit(words.random())
    }
    counter.incrementAndGet()
}

fun callFlow() = runBlocking {
    val randomWordFlow = randomWordFlow()
    randomWordFlow.collect { word -> print(word) }
    println(counter.get())
    return@runBlocking randomWordFlow
}

fun main(): Unit = runBlocking {
    val callFlow = callFlow()
    repeat(5) {
        callFlow.collect { word -> print(word) }
        println(counter.get())
    }
}


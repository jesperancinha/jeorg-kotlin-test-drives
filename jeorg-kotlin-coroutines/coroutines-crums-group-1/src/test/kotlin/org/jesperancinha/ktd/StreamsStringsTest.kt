package org.jesperancinha.ktd

import io.kotest.matchers.string.shouldContain
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class StreamsStringsTest {

    private val streamsStrings by lazy { StreamsStrings() }

    @Test
    fun `should run command correctly`(): Unit = runBlocking {

        val executeCommand = streamsStrings.executeCommand(listOf("java", "-version"))
        println(executeCommand)
        executeCommand.errorMessage shouldContain "JDK"
    }
}
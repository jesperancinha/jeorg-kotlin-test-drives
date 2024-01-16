package org.jesperancinha.ktd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.InputStream

class StreamsStrings {
    suspend fun executeCommand(commandArgs: List<String>): ProcessResult = withContext(
        Dispatchers.IO
    ) {
        runCatching {
            val process = ProcessBuilder(commandArgs).start()
            val outputStream = async {
                println("Context for output stream -> $coroutineContext -> Thread -> ${Thread.currentThread()}")
                readStream(process.inputStream) }
            val errorStream = async {
                println("Context for error stream -> $coroutineContext -> Thread -> ${Thread.currentThread()}")
                readStream(process.errorStream)
            }
            println("Context for exit code -> $coroutineContext -> Thread -> ${Thread.currentThread()}")
            val exitCode =  process.waitFor()
            ProcessResult(
                exitCode = exitCode,
                message = outputStream.await(),
                errorMessage = errorStream.await()
            )
        }.onFailure{
            ProcessResult(
                exitCode = -1,
                message = "",
                errorMessage = it.localizedMessage
            )
        }.getOrThrow()
    }

    private fun readStream(inputStream: InputStream) =
        inputStream.bufferedReader().use { reader -> reader.readText() }

    data class ProcessResult(val exitCode: Int, val message: String, val errorMessage: String)
}
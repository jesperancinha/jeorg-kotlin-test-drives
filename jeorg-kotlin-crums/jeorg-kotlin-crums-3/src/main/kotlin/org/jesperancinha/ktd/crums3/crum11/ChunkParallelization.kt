package org.jesperancinha.ktd.crums3.crum11

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.time.LocalDateTime
import java.util.*

class ChunkParallelization {
    companion object {
        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().magenta(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {

            logger.infoTitle("Crum 11 - Chunk Parallelization")

            listOf(
                "Lars Pereira",
                "Fenestev Moon",
                "Mrs. Rose",
                "Lucy Dalia",
                "The Beast",
                "Madame Pat",
                "Kitten",
                "Sneller Brass"
            ).chunked(2).flatMap { chunk ->
                val chunkId = UUID.randomUUID()
                chunk.map {
                    async {
                        delay((Math.random() * 100).toLong())
                        logger.info("Chunk $chunkId run at ${LocalDateTime.now()} with letter $it")
                    }
                }
            }.awaitAll()
        }
    }
}
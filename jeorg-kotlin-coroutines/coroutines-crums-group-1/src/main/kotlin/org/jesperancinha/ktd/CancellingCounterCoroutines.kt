package org.jesperancinha.ktd

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.slf4j.LoggerFactory
import java.util.UUID

private val logger = LoggerFactory.getLogger(CancellingCounterCoroutines::class.java)

class CancellingCounterCoroutines {

    companion object {

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runBlocking {
                produce()
                    .take(10)
                    .onCompletion {
                        it?.printStackTrace()
                    }
                    .collect {
                        logger.info("Received $it")
                    }
            }
            logger.info("Done!")
        }

        private fun produce() = flow {
            try {
                while (true) {
                    emit(UUID.randomUUID())
                    if (condition()) currentCoroutineContext().cancel()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                logger.info("Production is complete!")
            }

        }.flowOn(Dispatchers.IO)

        private fun condition() = false
    }
}



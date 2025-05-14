package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.manager.ProcessorAbstract
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future

abstract class QueueCallableAbstract : QueueCallable {
    private val allResults: MutableList<Future<Boolean>> = ArrayList()
    override val allCallables: MutableList<Callable<Boolean>> = ArrayList()
    override fun waitForCalls() {
        waitForAllFutures(allResults, logger)
    }

    override fun addSubmitResult(submitResult: Future<Boolean>): Unit = let { allResults.add(submitResult) }

    companion object {
        private val logger = LoggerFactory.getLogger(QueueCallableAbstract::class.java)
        fun waitForAllFutures(allResults: MutableList<Future<Boolean>>, logger: Logger) {
            allResults.forEach { booleanFuture: Future<Boolean> ->
                try {
                    if (booleanFuture.get() != null && !booleanFuture.get()!!) {
                        logger.error(ProcessorAbstract.SCHEDULED_TASK_FAILED_TO_EXECUTE)
                    }
                } catch (e: NullPointerException) {
                    logger.error(e.message, e)
                } catch (e: InterruptedException) {
                    logger.error(e.message, e)
                } catch (e: ExecutionException) {
                    logger.error(e.message, e)
                }
            }
        }
    }
}
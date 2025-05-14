package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.queues.Queue
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.function.Consumer

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
abstract class ProcessorAbstract {
    abstract val executorServiceQueue: Queue
    open fun waitForAllCalls(queueCallable: QueueCallable) {
        queueCallable.waitForCalls()
    }

    abstract fun getExecutorName(callable: Callable<Boolean>): String
    open fun runAllCalls(queueCallable: QueueCallable) {
        queueCallable.allCallables.forEach { booleanCallable ->
                val executorServiceMap = executorServiceQueue.executorServiceMap
                val executorName = getExecutorName(booleanCallable)
                val threadPoolExecutor = executorServiceMap[executorName]
                val submitResult = threadPoolExecutor!!.submit(booleanCallable)
                addSubmitResult(submitResult, queueCallable)
            }
    }

     private fun addSubmitResult(submitResult: Future<Boolean>, queueCallable: QueueCallable) {
        queueCallable.addSubmitResult(submitResult)
    }

    companion object {
        const val SCHEDULED_TASK_FAILED_TO_EXECUTE = "scheduled task faild to execute!"
    }
}
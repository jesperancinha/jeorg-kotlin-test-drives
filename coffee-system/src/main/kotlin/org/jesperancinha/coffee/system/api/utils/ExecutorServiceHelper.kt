package org.jesperancinha.coffee.system.api.utils

import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
object ExecutorServiceHelper {
    fun shutDownExecutorService(executorService: ExecutorService) {
        executorService.shutdown()
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            executorService.shutdownNow()
        }
    }
}
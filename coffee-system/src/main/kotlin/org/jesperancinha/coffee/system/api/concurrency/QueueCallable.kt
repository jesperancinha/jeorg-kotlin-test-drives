package org.jesperancinha.coffee.system.api.concurrency

import java.util.concurrent.Callable
import java.util.concurrent.Future

/**
 * Created by joao on 8-5-16.
 */
interface QueueCallable : Callable<Boolean> {
    fun addSubmitResult(submitResult: Future<Boolean>)
    fun waitForCalls()
    val allCallables: MutableList<Callable<Boolean>>
}
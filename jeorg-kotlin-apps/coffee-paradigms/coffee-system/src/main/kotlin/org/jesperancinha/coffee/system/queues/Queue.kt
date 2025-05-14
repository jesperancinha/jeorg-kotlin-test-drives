package org.jesperancinha.coffee.system.queues

import org.jesperancinha.coffee.system.api.utils.ExecutorServiceHelper
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.function.Consumer

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
abstract class Queue {
    val executorServiceMap: MutableMap<String, ThreadPoolExecutor> = HashMap()
    val numberToCreateMap: MutableMap<String, Int> = HashMap()

    /**
     * Number of machines of the same name
     *
     * @param queueSize
     * @param name
     */
    fun setQueueSize(queueSize: Int, name: String) {
        var currentSize = numberToCreateMap[name]
        if (currentSize == null) {
            currentSize = 0
        }
        numberToCreateMap[name] = currentSize + queueSize
    }

    fun getExecutor(name: String): ThreadPoolExecutor? {
        return executorServiceMap[name]
    }

    fun initExecutors() {
        numberToCreateMap.keys.forEach(
            Consumer { name: String ->
                val currentExecutor = getExecutor(name)
                if (currentExecutor != null) {
                    ExecutorServiceHelper.shutDownExecutorService(currentExecutor)
                }
                val managedExecutorService =  //
                    Executors.newFixedThreadPool(numberToCreateMap[name]!!) as ThreadPoolExecutor
                executorServiceMap[name] = managedExecutorService
            }
        )
    }

    fun stopExecutors() {
        executorServiceMap.values.forEach { ExecutorServiceHelper.shutDownExecutorService(it) }
    }
}
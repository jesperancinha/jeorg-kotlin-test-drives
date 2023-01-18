package com.jesperancinha.coffee.system.queues;

import com.jesperancinha.coffee.system.api.queues.CPQueue;
import com.jesperancinha.coffee.system.api.utils.ExecutorServiceHelper;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Getter
public abstract class Queue implements CPQueue {
    private Map<String, ThreadPoolExecutor> executorServiceMap = new HashMap<>();
    private Map<String, Integer> numberToCreateMap = new HashMap<>();

    /**
     * Number of machines of the same name
     *
     * @param queueSize
     * @param name
     */
    @Override
    public void setQueueSize(int queueSize, String name) {
        Integer currentSize = numberToCreateMap.get(name);
        if (currentSize == null) {
            currentSize = 0;
        }
        numberToCreateMap.put(name, currentSize + queueSize);
    }

    @Override
    public ThreadPoolExecutor getExecutor(String name) {
        return executorServiceMap.get(name);
    }

    @Override
    public void initExecutors() {
        numberToCreateMap.keySet().forEach(
                name -> {
                    final ThreadPoolExecutor currentExecutor = getExecutor(name);
                    if (currentExecutor != null) {
                        ExecutorServiceHelper.shutDownExecutorService(currentExecutor);
                    }
                    final ThreadPoolExecutor managedExecutorService = //
                            (ThreadPoolExecutor) Executors.newFixedThreadPool(numberToCreateMap.get(name));
                    executorServiceMap.put(name, managedExecutorService);
                }
        );
    }

    @Override
    public void stopExecutors() {
        executorServiceMap.values().forEach(
                ExecutorServiceHelper::shutDownExecutorService
        );
    }
}

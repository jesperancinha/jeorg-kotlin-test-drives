package com.steelzack.coffee.system.queues;

import com.steelzack.coffee.system.utils.ExecutorServiceHelper;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Getter
public class QueueAbstract {
    private Map<String, ThreadPoolExecutor> executorServiceMap = new HashMap<>();
    private Map<String, Integer> numberToCreateMap = new HashMap<>();

    public void setQueueSize(int queueSize, String name) {
        Integer currentSize = numberToCreateMap.get(name);
        if (currentSize == null) {
            currentSize = 0;
        }
        numberToCreateMap.put(name, currentSize + queueSize);
    }

    public ExecutorService getExecutor(String name) {
        return executorServiceMap.get(name);
    }

    public void initExecutors() {
        numberToCreateMap.keySet().stream().forEach(
                name -> {
                    final ThreadPoolExecutor currentExecutor = executorServiceMap.get(name);
                    if (currentExecutor != null) {
                        ExecutorServiceHelper.shutDownExecutorService(currentExecutor);
                    }
                    final ThreadPoolExecutor managedExecutorService = //
                            (ThreadPoolExecutor) Executors.newFixedThreadPool(numberToCreateMap.get(name));
                    executorServiceMap.put(name, managedExecutorService);
                }
        );
    }

    public void stopExecutors() {
        executorServiceMap.values().stream().forEach(
                ExecutorServiceHelper::shutDownExecutorService
        );
    }
}

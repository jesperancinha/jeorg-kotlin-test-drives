package com.jesperancinha.coffee.system.api.queues;

import java.util.concurrent.ExecutorService;

/**
 * Created by joaofilipesabinoesperancinha on 07-05-16.
 */
public interface CPQueue {
    void setQueueSize(int queueSize, String name);

    ExecutorService getExecutor(String name);

    void initExecutors();

    void stopExecutors();

    java.util.Map<String, java.util.concurrent.ThreadPoolExecutor> getExecutorServiceMap();
}

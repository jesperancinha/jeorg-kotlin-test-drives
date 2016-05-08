package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.QueueCallable;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public interface Processor {
    void addQueueSize(int queueSize, String name);

    void initExecutors();

    void stopExectutors();

    void waitForAllCalls(QueueCallable queueCallable);

    void runAllCalls(QueueCallable queueCallable);
}

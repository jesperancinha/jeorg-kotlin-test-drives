package com.steelzack.coffee.system.queues;

import com.steelzack.coffee.system.utils.ExecutorServiceHelper;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Getter
public class QueueAbstract {
    private ExecutorService managedExecutorService;

    public void setQueueSize(int queueSize)
    {
        ExecutorServiceHelper.shutDownExecutorService(managedExecutorService);
        managedExecutorService = Executors.newFixedThreadPool(queueSize);
    }
}

package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.utils.ExecutorServiceHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public abstract class ProcessorImpl {

    ExecutorService managedExecutorService;

    public void setQueueSize(int queueSize)
    {
        ExecutorServiceHelper.shutDownExecutorService(managedExecutorService);
        managedExecutorService = Executors.newFixedThreadPool(queueSize);
    }


}

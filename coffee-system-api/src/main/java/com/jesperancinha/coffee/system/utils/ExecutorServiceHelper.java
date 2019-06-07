package com.jesperancinha.coffee.system.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public class ExecutorServiceHelper {
    public static void shutDownExecutorService(ExecutorService executorService) {
        if(executorService!= null)
        {
            executorService.shutdown();
            try {
                executorService.awaitTermination(60, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
}

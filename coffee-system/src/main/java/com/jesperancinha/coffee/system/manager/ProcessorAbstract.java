package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.queues.QueueAbstract;
import com.jesperancinha.coffee.system.concurrency.QueueCallable;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public abstract class ProcessorAbstract {

    public static final String SCHEDULED_TASK_FAILED_TO_EXECUTE = "scheduled task faild to execute!";

    private static final Logger logger = Logger.getLogger(ProcessorAbstract.class);

    public abstract QueueAbstract getExecutorServiceQueue();

    public void waitForAllCalls(QueueCallable queueCallable) {
       queueCallable.waitForCalls();
    }


    public abstract String getExecutorName(Callable<Boolean> callable);

    public void runAllCalls(QueueCallable queueCallable) {
        queueCallable.getAllCallables().stream().forEach(
                booleanCallable -> {
                    final Map<String, ThreadPoolExecutor> executorServiceMap = getExecutorServiceQueue().getExecutorServiceMap();
                    final String executorName = getExecutorName(booleanCallable);
                    final ThreadPoolExecutor threadPoolExecutor = executorServiceMap.get(executorName);
                    final Future<Boolean> submitResult = threadPoolExecutor.submit(booleanCallable);
                    addSubmitResult(submitResult, queueCallable);
                } //
        );    }

    private void addSubmitResult(Future<Boolean> submitResult, QueueCallable queueCallable) {
        queueCallable.addSubmitResult(submitResult);
    }
}

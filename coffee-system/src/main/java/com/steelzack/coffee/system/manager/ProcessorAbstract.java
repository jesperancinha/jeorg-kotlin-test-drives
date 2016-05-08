package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.queues.QueueAbstract;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public abstract class ProcessorAbstract {

    public static final String SCHEDULED_TASK_FAILED_TO_EXECUTE = "scheduled task faild to execute!";

    private static final Logger logger = Logger.getLogger(ProcessorAbstract.class);

    private final List<Future<Boolean>> allResults = new ArrayList<>();

    private final List<Callable<Boolean>> allCallables = new ArrayList<>();

    public abstract QueueAbstract getExecutorServiceQueue();

    private ReentrantLock lockResult = new ReentrantLock();

    private ReentrantLock lockCallables = new ReentrantLock();

    public void waitForAllCalls() {
        getCopyOfAllResults().stream().forEach( //
                booleanFuture -> { //
                    try { //
                        if (booleanFuture.get() != null && !booleanFuture.get()) { //
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE); //
                        }
                    } catch (NullPointerException | InterruptedException | ExecutionException e) { //
                        logger.error(e.getMessage(), e); //
                    }
                }
        );
    }


    public abstract String getExecutorName(Callable<Boolean> callable);

    public void runAllCalls() {
        getCopyOfAllCallables().stream().forEach(
                booleanCallable -> {
                    final Map<String, ThreadPoolExecutor> executorServiceMap = getExecutorServiceQueue().getExecutorServiceMap();
                    final String executorName = getExecutorName(booleanCallable);
                    final ThreadPoolExecutor threadPoolExecutor = executorServiceMap.get(executorName);
                    final Future<Boolean> submitResult = threadPoolExecutor.submit(booleanCallable);
                    addSubmitResult(submitResult);
                } //
        );
    }


    private void addSubmitResult(Future<Boolean> submitResult) {
        lockResult.lock();
        allResults.add(submitResult);
        lockResult.unlock();
    }

    private List<Future<Boolean>> getCopyOfAllResults() {
        lockResult.lock();
        final List<Future<Boolean>> newCopy = new ArrayList<>();
        allResults.stream().map(newCopy::add);
        lockResult.unlock();
        return newCopy;

    }

    void addCallable(Callable<Boolean> callable){
        lockCallables.lock();
        allCallables.add(callable);
        lockCallables.unlock();
    }

    private List<Callable<Boolean>> getCopyOfAllCallables() {
        lockCallables.lock();
        final List<Callable<Boolean>> newCopy = new ArrayList<>();
        allCallables.stream().map(newCopy::add);
        lockCallables.unlock();
        return allCallables;
    }

}

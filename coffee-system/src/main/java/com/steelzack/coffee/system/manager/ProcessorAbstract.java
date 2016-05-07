package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.queues.QueueAbstract;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.steelzack.coffee.system.concurrency.EmployeeCallableImpl.SCHEDULED_TASK_FAILED_TO_EXECUTE;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public abstract class ProcessorAbstract {

    private static final Logger logger = Logger.getLogger(ProcessorAbstract.class);

    final Set<Future<Boolean>> allResults = new HashSet<>();

    final Set<Callable<Boolean>> allCallables = new HashSet<>();

    public abstract QueueAbstract getExecutorService();

    public void waitForAllCalls() {
        allResults.stream().forEach( //
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
        allCallables.stream().forEach(
                booleanCallable -> getExecutorService().getExecutorServiceMap().get(getExecutorName(booleanCallable)).submit(booleanCallable) //
        );
    }
}

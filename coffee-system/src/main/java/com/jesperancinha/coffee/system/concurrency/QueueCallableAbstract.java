package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.jesperancinha.coffee.system.manager.ProcessorAbstract.SCHEDULED_TASK_FAILED_TO_EXECUTE;

@Getter
public abstract class QueueCallableAbstract implements QueueCallable {
    private final static Logger logger = LoggerFactory.getLogger(QueueCallableAbstract.class);

    private final List<Future<Boolean>> allResults = new ArrayList<>();

    private final List<Callable<Boolean>> allCallables = new ArrayList<>();

    static void waitForAllFutures(List<Future<Boolean>> allResults, Logger logger) {
        allResults.forEach(
                booleanFuture -> {
                    try {
                        if (booleanFuture.get() != null && !booleanFuture.get()) {
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
                        }
                    } catch (NullPointerException | InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
        );
    }

    @Override
    public void waitForCalls() {
        waitForAllFutures(allResults, logger);
    }

    @Override
    public List<Callable<Boolean>> getAllCallables() {
        return allCallables;
    }

    @Override
    public void addSubmitResult(Future<Boolean> submitResult) {
        allResults.add(submitResult);
    }


}

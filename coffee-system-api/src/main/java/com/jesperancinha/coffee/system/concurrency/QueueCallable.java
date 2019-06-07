package com.jesperancinha.coffee.system.concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by joao on 8-5-16.
 */
public interface QueueCallable extends Callable<Boolean> {
    void addSubmitResult(Future<Boolean> submitResult);

    void waitForCalls();

    List<Callable<Boolean>> getAllCallables();
}

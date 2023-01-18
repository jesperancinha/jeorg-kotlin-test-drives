package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by joao on 8-5-16.
 */
public abstract class StartupCallableImpl extends QueueCallableAbstract implements QueueCallable {
    @Override
    public Boolean call() throws Exception {
        return true;
    }

    public abstract List<Callable<Boolean>> getAllCallables();
}

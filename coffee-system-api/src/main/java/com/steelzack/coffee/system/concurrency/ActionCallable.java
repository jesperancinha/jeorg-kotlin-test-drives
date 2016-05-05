package com.steelzack.coffee.system.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
public abstract class ActionCallable implements Callable<Boolean>, Action {

    final String description;

    final Byte time;

    ActionCallable(String description, Byte time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public Boolean call() throws Exception {
        TimeUnit.MILLISECONDS.wait(time.longValue());
        return true;
    }

}

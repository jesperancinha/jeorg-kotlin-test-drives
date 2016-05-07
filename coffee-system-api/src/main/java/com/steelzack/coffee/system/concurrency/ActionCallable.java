package com.steelzack.coffee.system.concurrency;

import java.util.concurrent.Callable;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
public abstract class ActionCallable implements Callable<Boolean> {

    final String description;

    final int time;

    ActionCallable(String description, Integer time) {
        this.description = description;
        this.time = time;
    }

}

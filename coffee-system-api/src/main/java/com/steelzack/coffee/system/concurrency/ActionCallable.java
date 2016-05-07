package com.steelzack.coffee.system.concurrency;

import lombok.Getter;

import java.util.concurrent.Callable;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
public abstract class ActionCallable implements Callable<Boolean> {

    final String description;

    final int time;
    private String name;

    ActionCallable(String description, Integer time, String name) {
        this.description = description;
        this.time = time;
        this.name = name;
    }

}

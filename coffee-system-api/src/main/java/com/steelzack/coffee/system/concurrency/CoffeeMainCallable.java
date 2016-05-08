package com.steelzack.coffee.system.concurrency;

import java.util.concurrent.Callable;

/**
 * Created by joao on 7-5-16.
 */
public interface CoffeeMainCallable extends Callable<Boolean> {
    String getName();
}

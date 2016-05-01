package com.steelzack.coffee.system.concurrency;

import lombok.Getter;

import java.util.concurrent.Callable;

/**
 * Created by joao on 29-4-16.
 */
@Getter
public class CoffeeCallableImpl implements Coffe, Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        return true;
    }
}

package com.steelzack.coffee.system.concurrency;

import lombok.Getter;

import java.util.concurrent.Callable;

@Getter
public class EmployeeCallableImpl implements Employee, Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        return true;
    }
}

package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;

import java.util.concurrent.Callable;

/**
 * Created by joao on 8-5-16.
 */
public interface PreActionCallable extends Callable<Boolean> {
    void addPreAction(Employees.Employee.Actions.PreAction preAction);
}

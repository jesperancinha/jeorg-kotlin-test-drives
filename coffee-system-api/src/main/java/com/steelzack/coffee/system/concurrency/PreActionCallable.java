package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;

import java.util.concurrent.Callable;

/**
 * Created by joao on 8-5-16.
 */
public interface PreActionCallable extends Callable<Boolean> {

    void addPreAction(PreAction preAction);

}

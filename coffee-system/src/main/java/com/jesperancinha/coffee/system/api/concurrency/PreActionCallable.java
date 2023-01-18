package com.jesperancinha.coffee.system.api.concurrency;

import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;

/**
 * Created by joao on 8-5-16.
 */
public interface PreActionCallable extends QueueCallable {

    void addPreAction(PreAction preAction);

}

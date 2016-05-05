package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public class PreActionCallableImpl extends ActionCallableImpl {

    public PreActionCallableImpl(PreAction preAction) {
        super(preAction.getDescription(), preAction.getTime());
    }
}

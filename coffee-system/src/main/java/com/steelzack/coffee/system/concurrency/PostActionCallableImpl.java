package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public class PostActionCallableImpl extends ActionCallable {

    public PostActionCallableImpl(Employees.Employee.Actions.PostAction postAction) {
        super(postAction.getDescription(), postAction.getTime());
    }

}

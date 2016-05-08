package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;

import java.util.concurrent.Callable;

/**
 * Created by joao on 8-5-16.
 */
public interface PostActionCallable extends Callable<Boolean> {

    void addPostAction(PostAction postAction);

}

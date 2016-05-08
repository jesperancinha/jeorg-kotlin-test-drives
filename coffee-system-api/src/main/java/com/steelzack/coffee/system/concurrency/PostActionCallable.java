package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;

/**
 * Created by joao on 8-5-16.
 */
public interface PostActionCallable extends QueueCallable {

    void addPostAction(PostAction postAction);

}

package com.jesperancinha.coffee.system.api.concurrency;

import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;

/**
 * Created by joao on 8-5-16.
 */
public interface PostActionCallable extends QueueCallable {

    void addPostAction(PostAction postAction);

}

package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import lombok.Getter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
public class ActionCallableImpl implements Callable<Boolean>, Action{

    private final String description;

    private final Byte time;

    public ActionCallableImpl(PreAction preAction)
    {
        this.description = preAction.getDescription();
        this.time = preAction.getTime();
    }

    public ActionCallableImpl(PostAction postAction)
    {
        this.description = postAction.getDescription();
        this.time = postAction.getTime();
    }

    @Override
    public Boolean call() throws Exception {
        TimeUnit.MILLISECONDS.wait(time.longValue());
        return true;
    }

}

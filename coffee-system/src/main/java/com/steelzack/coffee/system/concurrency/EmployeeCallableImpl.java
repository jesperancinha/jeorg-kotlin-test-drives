package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import lombok.Getter;

import java.util.concurrent.Callable;

@Getter
public class EmployeeCallableImpl implements Employee, Callable<Boolean> {

    private final Actions actions;

    public EmployeeCallableImpl(Actions actions){
        this.actions = actions;
    }

    @Override
    public Boolean call() throws Exception {
        return true;
    }
}

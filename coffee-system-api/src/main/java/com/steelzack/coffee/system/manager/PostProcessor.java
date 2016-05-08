package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.QueueCallable;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;

import java.util.List;

public interface PostProcessor extends Processor{

    void callPostActions(Employees.Employee employee, String name, List<Actions.PostAction> postActions, QueueCallable parentCallable);
}

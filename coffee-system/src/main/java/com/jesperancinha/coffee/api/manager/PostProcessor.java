package com.jesperancinha.coffee.api.manager;

import com.jesperancinha.coffee.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.system.input.Employees;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions;

import java.util.List;

public interface PostProcessor extends Processor {

    void callPostActions(Employees.Employee employee, String name, List<Actions.PostAction> postActions, QueueCallable parentCallable);
}

package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.QueueCallable;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;

import java.util.List;

public interface PostProcessor extends Processor{

    void setActions(List<Actions.PostAction> actions);

    void callPostActions(String name, QueueCallable parentCallable);
}

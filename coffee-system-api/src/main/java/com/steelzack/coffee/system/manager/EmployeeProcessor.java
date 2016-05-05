package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.Employees.Employee.Actions;

public interface EmployeeProcessor extends Processor{

    void setActions(Actions actions);

    void callPreActions(String name);

    void callPostActions(String name);

    void setPostQueueSize(int queueSize, String name);
}

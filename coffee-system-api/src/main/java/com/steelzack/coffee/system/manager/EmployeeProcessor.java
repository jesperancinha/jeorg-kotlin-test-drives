package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.Employees;

public interface EmployeeProcessor extends Processor{

    void setActions(Employees.Employee.Actions actions);

    void callPreActions();

    void callPostActions();

    void setPostQueueSize(int queueSize);

}

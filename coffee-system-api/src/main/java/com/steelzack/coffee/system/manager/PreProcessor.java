package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.Employees.Employee.Actions;

import java.util.List;

public interface PreProcessor extends Processor {

    void setActions(List<Actions.PreAction> actions);

    void callPreActions(String name);
}

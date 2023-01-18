package com.jesperancinha.coffee.system.api.manager;

import org.jesperancinha.coffee.system.input.CoffeeMachines;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import org.jesperancinha.coffee.system.input.Employees;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions;

import java.util.List;

public interface PreProcessor extends Processor {

    void callPreActions(
            Employees.Employee employee,
            String name,
            List<Actions.PreAction> actions,
            Coffee coffee, CoffeeMachines.CoffeMachine.PaymentTypes.Payment payment,
            List<Actions.PostAction> postActions
    );


    void runAllCalls();

    void waitForAllCalls();
}

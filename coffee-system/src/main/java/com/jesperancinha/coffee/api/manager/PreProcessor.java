package com.jesperancinha.coffee.api.manager;

import com.jesperancinha.coffee.system.input.CoffeeMachines;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.jesperancinha.coffee.system.input.Employees;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions;

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

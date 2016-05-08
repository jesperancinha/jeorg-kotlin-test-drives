package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees;

import java.util.List;

/**
 * Created by joao on 29-4-16.
 */
public interface CoffeeProcessor extends Processor {
    void setChosenCoffee(Coffee chosenCoffee, Payment payment, List<Employees.Employee.Actions.PostAction> postActions);

    void callMakeCoffee(String name);
}

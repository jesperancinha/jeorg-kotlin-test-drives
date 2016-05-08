package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface MachineProcessor {

    PreProcessor getPreProcessor();
    
    CoffeeProcessor getCoffeeProcessor();

    PaymentProcessor getPaymentProcessor();

    PostProcessor getPostProcessor();

    void callPreActions(Employee employee, String name, List<PreAction> preActions, Coffee coffee, Payment payment, List<PostAction> postActions);

    void callMakeCoffee(Employee employee, String name, Coffee coffee, Payment payment, List<PostAction> postActions);

    void callPayCoffee(String name);

    void callPostActions(String name);

    void initAll();

}

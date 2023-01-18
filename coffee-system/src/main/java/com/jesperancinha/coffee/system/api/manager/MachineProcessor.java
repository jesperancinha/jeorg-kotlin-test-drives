package com.jesperancinha.coffee.system.api.manager;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import org.jesperancinha.coffee.system.input.Employees.Employee;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;

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

    void callMakeCoffee(Employee employee, String name, Coffee coffee, Payment payment, List<PostAction> postActions, QueueCallable parentCallable);

    void callPayCoffee(Employee employee, String name, Payment payment, List<PostAction> postActions, QueueCallable parentCallable);

    void callPostActions(Employee employee, String name, List<PostAction> postActions, QueueCallable parentCallable);

    void initAll();

}

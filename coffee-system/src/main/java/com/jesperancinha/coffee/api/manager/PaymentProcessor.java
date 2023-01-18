package com.jesperancinha.coffee.api.manager;

import com.jesperancinha.coffee.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees.Employee;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface PaymentProcessor extends Processor {

    void callPayCoffee(Employee employee, String name, Payment payment, List<PostAction> postActions, QueueCallable parentCallable);

}

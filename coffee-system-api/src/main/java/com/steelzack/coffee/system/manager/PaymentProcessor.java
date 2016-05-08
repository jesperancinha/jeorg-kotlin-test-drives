package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.QueueCallable;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface PaymentProcessor extends Processor {

    void setChosenPayment(Payment chosenPayment, List<PostAction> postActions);

    void callPayCoffee(String name, QueueCallable callableParent);
}

package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface PaymentProcessor extends Processor {

    void setChosenPayment(Payment chosenPayment);

    void callPayCoffee(String name);
}

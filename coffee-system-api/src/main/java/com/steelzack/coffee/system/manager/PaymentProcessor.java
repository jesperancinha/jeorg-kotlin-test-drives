package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface PaymentProcessor extends Processor {

    void setChosenPayment(CoffeeMachines.CoffeMachine.PaymentTypes.Payment chosenPayment);

    void callPayCoffee();

}

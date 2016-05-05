package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;

/**
 * Created by joao on 29-4-16.
 */
public interface CoffeeProcessor extends Processor {
    void setChosenCoffee(CoffeeMachines.CoffeMachine.Coffees.Coffee chosenCoffee);

    void callMakeCoffee();
}

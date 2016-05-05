package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;

/**
 * Created by joao on 29-4-16.
 */
public interface CoffeeProcessor extends Processor {
    void setChosenCoffee(Coffee chosenCoffee);

    void callMakeCoffee(String name);
}

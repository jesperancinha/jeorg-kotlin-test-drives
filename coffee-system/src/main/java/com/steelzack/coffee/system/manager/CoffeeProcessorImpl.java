package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by joao on 29-4-16.
 */
@Builder
@Getter
public class CoffeeProcessorImpl implements CoffeeProcessor{
    private final Coffee coffee;
}
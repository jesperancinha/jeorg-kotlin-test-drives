package com.steelzack.coffee.system.manager;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Builder
@Getter
public class MachineProcessorImpl implements MachineProcessor {
    private final CoffeeProcessor coffeeProcessor;

    private final PaymentProcessor paymentProcessor;
}

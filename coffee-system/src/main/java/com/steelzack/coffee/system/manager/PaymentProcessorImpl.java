package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Builder
@Getter
public class PaymentProcessorImpl implements PaymentProcessor {
    private final Payment payment;
}

package com.steelzack.coffee.system.manager;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Getter
@Service
public class MachineProcessorImpl implements MachineProcessor {

    @Autowired
    private PreProcessor preProcessor;

    @Autowired
    private CoffeeProcessor coffeeProcessor;

    @Autowired
    private PaymentProcessor paymentProcessor;

    @Autowired
    private PostProcessor postProcessor;

    @Override
    public void callPreActions(String name) {
        preProcessor.callPreActions(name);
    }

    @Override
    public void callMakeCoffee(String name) {
        coffeeProcessor.callMakeCoffee(name);
    }

    @Override
    public void callPayCoffee(String name) {
        paymentProcessor.callPayCoffee(name);
    }

    @Override
    public void callPostActions(String name) {
        postProcessor.callPostActions(name);
    }

    @Override
    public void initAll()
    {
        preProcessor.initExecutors();
        coffeeProcessor.initExecutors();
        paymentProcessor.initExecutors();
        postProcessor.initExecutors();
    }
}

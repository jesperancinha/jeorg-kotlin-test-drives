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
    private EmployeeProcessor employeeProcessor;

    @Autowired
    private CoffeeProcessor coffeeProcessor;

    @Autowired
    private PaymentProcessor paymentProcessor;

    @Override
    public void callPreActions(String name) {
        employeeProcessor.callPreActions(name);
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
        employeeProcessor.callPostActions(name);
    }

    @Override
    public void initAll()
    {
        employeeProcessor.initExecutors();
        coffeeProcessor.initExecutors();
        paymentProcessor.initExecutors();
    }
}

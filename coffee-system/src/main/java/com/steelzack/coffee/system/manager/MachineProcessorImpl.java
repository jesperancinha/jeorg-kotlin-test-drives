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
}

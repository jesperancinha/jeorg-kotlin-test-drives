package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.api.manager.*;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees.Employee;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void callPreActions(Employee employee, String name, List<PreAction> preActions, Coffee coffee, Payment payment, List<PostAction> postActions) {
        preProcessor.callPreActions(employee, name, preActions, coffee, payment, postActions);
    }

    @Override
    public void callMakeCoffee(Employee employee, String name, Coffee coffee, Payment payment, List<PostAction> postActions, QueueCallable parentCallable) {
        coffeeProcessor.callMakeCoffee(employee, name, coffee, payment, postActions, parentCallable);
    }

    @Override
    public void callPayCoffee(Employee employee, String name, Payment payment, List<PostAction> postActions, QueueCallable parentCallable) {
        paymentProcessor.callPayCoffee(employee, name, payment, postActions, parentCallable);
    }

    @Override
    public void callPostActions(Employee employee, String name, List<PostAction> postActions, QueueCallable parentCallable) {
        postProcessor.callPostActions(employee, name, postActions, parentCallable);
    }

    @Override
    public void initAll() {
        preProcessor.initExecutors();
        coffeeProcessor.initExecutors();
        paymentProcessor.initExecutors();
        postProcessor.initExecutors();
    }
}

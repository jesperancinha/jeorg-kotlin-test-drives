package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import org.jesperancinha.coffee.system.input.Employees.Employee;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Accessors(chain = true)
@Getter
@Service
public class MachineProcessorImpl {

    @Autowired
    private PreProcessorImpl preProcessor;

    @Autowired
    private CoffeeProcessorImpl coffeeProcessor;

    @Autowired
    private PaymentProcessorImpl paymentProcessor;

    @Autowired
    private PostProcessorImpl postProcessor;

    public void callPreActions(Employee employee, String name, List<PreAction> preActions, Coffee coffee, Payment payment, List<PostAction> postActions) {
        preProcessor.callPreActions(employee, name, preActions, coffee, payment, postActions);
    }

    public void callMakeCoffee(Employee employee, String name, Coffee coffee, Payment payment, List<PostAction> postActions, QueueCallable parentCallable) {
        coffeeProcessor.callMakeCoffee(employee, name, coffee, payment, postActions, parentCallable);
    }

    public void callPayCoffee(Employee employee, String name, Payment payment, List<PostAction> postActions, QueueCallable parentCallable) {
        paymentProcessor.callPayCoffee(employee, name, payment, postActions, parentCallable);
    }

    public void callPostActions(Employee employee, String name, List<PostAction> postActions, QueueCallable parentCallable) {
        postProcessor.callPostActions(employee, name, postActions, parentCallable);
    }

    public void initAll() {
        preProcessor.initExecutors();
        coffeeProcessor.initExecutors();
        paymentProcessor.initExecutors();
        postProcessor.initExecutors();
    }
}

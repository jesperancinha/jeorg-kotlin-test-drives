package com.steelzack.coffee.system.manager;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface MachineProcessor {
    CoffeeProcessor getCoffeeProcessor();

    EmployeeProcessor getEmployeeProcessor();

    PaymentProcessor getPaymentProcessor();

    void callPreActions();

    void callMakeCoffee();

    void callPayCoffee();

    void callPostActions();
}

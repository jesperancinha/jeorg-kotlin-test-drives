package com.steelzack.coffee.system.manager;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface MachineProcessor {
    CoffeeProcessor getCoffeeProcessor();

    EmployeeProcessor getEmployeeProcessor();

    PaymentProcessor getPaymentProcessor();

    void callPreActions(String name);

    void callMakeCoffee(String name);

    void callPayCoffee(String name);

    void callPostActions(String name);

    void initAll();
}

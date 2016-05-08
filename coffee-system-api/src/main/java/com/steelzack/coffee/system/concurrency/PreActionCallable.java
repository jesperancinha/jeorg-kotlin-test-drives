package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.steelzack.coffee.system.manager.MachineProcessor;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by joao on 8-5-16.
 */
public interface PreActionCallable extends Callable<Boolean> {
    PreActionCallable setElements(String name, Coffee coffee, Payment payment, List<PostAction> postActions);

    void setMachineProcessor(MachineProcessor machineProcessor);

    void addPreAction(PreAction preAction);
}

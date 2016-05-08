package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.steelzack.coffee.system.manager.CoffeeProcessor;
import com.steelzack.coffee.system.manager.MachineProcessor;
import com.steelzack.coffee.system.objects.ActionDescriptor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
@Accessors(chain = true)
@NoArgsConstructor
public class PreActionCallableImpl extends ActionCallable implements PreActionCallable {
    private final static Logger logger = Logger.getLogger(PreActionCallableImpl.class);

    private MachineProcessor machineProcessor;
    private Coffee coffee;
    private Payment payment;
    private List<PostAction> postActions;
    private Employee employee;

    public PreActionCallableImpl(
            Employee employee,
            String name,
            Coffee coffee,
            Payment payment,
            List<PostAction> postActions,
            MachineProcessor machineProcessor
    ) {
        this.employee = employee;
        this.name = name;
        this.coffee = coffee;
        this.payment = payment;
        this.postActions = postActions;
        this.machineProcessor = machineProcessor;
    }

    @Override
    public void addPreAction(PreAction preAction) {
        this.actionDescriptorList.add(ActionDescriptor.builder().description(preAction.getDescription()).time(preAction.getTime()).build());
    }

    @Override
    public Boolean call() {
        logger.info(MessageFormat.format("EmployeeCallable {0} is waiting in line", employee.getName()));
        this.actionDescriptorList.stream().forEach(
                actionDescriptor -> {
                    logger.info(MessageFormat.format("Starting with {0}", actionDescriptor.getDescription()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(actionDescriptor.getTime());
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                }
        );

        final CoffeeProcessor coffeeProcessor = machineProcessor.getCoffeeProcessor();
        machineProcessor.callMakeCoffee(employee, coffee.getName(), coffee, payment, postActions, this);
        coffeeProcessor.runAllCalls(this);
        coffeeProcessor.waitForAllCalls(this);
        return true;
    }

}

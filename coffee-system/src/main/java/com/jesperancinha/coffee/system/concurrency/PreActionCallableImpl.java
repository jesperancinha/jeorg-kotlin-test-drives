package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.system.api.concurrency.PreActionCallable;
import com.jesperancinha.coffee.system.api.manager.CoffeeProcessor;
import com.jesperancinha.coffee.system.api.manager.MachineProcessor;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import org.jesperancinha.coffee.system.input.Employees.Employee;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.jesperancinha.coffee.system.objects.ActionDescriptor;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Accessors(chain = true)
public class PreActionCallableImpl extends ActionCallable implements PreActionCallable {
    private final static Logger logger = LoggerFactory.getLogger(PreActionCallableImpl.class);

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
        super(name);
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
        this.actionDescriptorList.forEach(
                actionDescriptor -> {
                    logger.info(MessageFormat.format("Starting with {0}", actionDescriptor.getDescription()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(actionDescriptor.getTime());
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(), e);
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

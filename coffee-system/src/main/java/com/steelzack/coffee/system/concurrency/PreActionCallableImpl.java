package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
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

    @Override
    public PreActionCallableImpl setElements(String name, Coffee coffee, Payment payment, List<PostAction> postActions) {
        this.name = name;
        this.coffee = coffee;
        this.payment = payment;
        this.postActions = postActions;
        return this;
    }

    @Override
    public void setMachineProcessor(MachineProcessor machineProcessor) {
        this.machineProcessor = machineProcessor;
    }

    @Override
    public void addPreAction(PreAction preAction) {
        this.actionDescriptorList.add(ActionDescriptor.builder().description(preAction.getDescription()).time(preAction.getTime()).build());
    }

    @Override
    public Boolean call() {
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
        coffeeProcessor.setChosenCoffee(coffee, payment, postActions);
        machineProcessor.callMakeCoffee(coffee.getName());
        coffeeProcessor.runAllCalls();
        coffeeProcessor.waitForAllCalls();
        coffeeProcessor.stopExectutors();
//        paymentProcessor.setChosenPayment(payment, postActions);
//        postProcessor.setActions(postActions);
//                    machineProcessor.callPayCoffee(payment.getName());
//                    machineProcessor.callPostActions(MAIN_QUEUE_POST);

        return true;
    }
}

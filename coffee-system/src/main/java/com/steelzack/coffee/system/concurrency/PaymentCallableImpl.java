package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.manager.MachineProcessor;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Service
@Getter
public class PaymentCallableImpl extends QueueCallableAbstract implements PaymentCallable {

    private static final Logger logger = Logger.getLogger(PaymentCallableImpl.class);

    @Autowired
    private MachineProcessor machineProcessor;

    private Employees.Employee employee;
    private final PaymentTypes.Payment chosenPayment;
    private String name;
    private List<PostAction> postActions;


    public PaymentCallableImpl(Employees.Employee employee, String name, PaymentTypes.Payment payment, List<PostAction> postActions, MachineProcessor machineProcessor) {
        super();
        this.employee = employee;
        this.chosenPayment = payment;
        this.name = name;
        this.postActions = postActions;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("PaymentCallable with {0}", chosenPayment.getName()));
        Integer time = chosenPayment.getTime();
        if(time != null) {
            TimeUnit.MILLISECONDS.sleep(time);
        }
        return true;
    }

}

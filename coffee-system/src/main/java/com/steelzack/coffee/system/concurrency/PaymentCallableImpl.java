package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes;
import com.steelzack.coffee.system.manager.MachineProcessor;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Service
@Getter
public class PaymentCallableImpl implements Payment, Callable<Boolean> {

    private static final Logger logger = Logger.getLogger(PaymentCallableImpl.class);

    @Autowired
    private MachineProcessor machineProcessor;

    private final PaymentTypes.Payment chosenPayment;
    private String name;

    public PaymentCallableImpl(PaymentTypes.Payment chosenPayment, String name)
    {
        super();
        this.chosenPayment = chosenPayment;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("Payment with {0}", chosenPayment.getName()));
        Integer time = chosenPayment.getTime();
        if(time != null) {
            TimeUnit.MILLISECONDS.sleep(time);
        }
        return true;
    }
}

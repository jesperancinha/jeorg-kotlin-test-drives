package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.PaymentCallableImpl;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueuePaymentImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.steelzack.coffee.system.concurrency.EmployeeCallableImpl.SCHEDULED_TASK_FAILED_TO_EXECUTE;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Getter
@Service
public class PaymentProcessorImpl extends ProcessorImpl implements PaymentProcessor {

    private static final Logger logger = Logger.getLogger(PaymentProcessorImpl.class);

    @Autowired
    private QueuePaymentImpl queuePayment;

    private Payment chosenPayment;

    @Override
    public void setChosenPayment(Payment chosenPayment) {
        this.chosenPayment = chosenPayment;
    }

    @Override
    public void callPayCoffee(String name) {
        try {
            if (!queuePayment.getExecutor(name).submit(new PaymentCallableImpl(chosenPayment)).get()) {
                logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public QueueAbstract getExecutorService() {
        return queuePayment;
    }

    @Override
    public void addQueueSize(int queueSize, String name) {
        queuePayment.setQueueSize(queueSize, name);
    }

    @Override
    public void initExecutors() {
        queuePayment.initExecutors();
    }
}

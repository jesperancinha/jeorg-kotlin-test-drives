package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.system.concurrency.PaymentCallableImpl;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import org.jesperancinha.coffee.system.input.Employees;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.queues.Queue;
import com.jesperancinha.coffee.system.queues.QueuePaymentImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Getter
@Service
public abstract class PaymentProcessorImpl extends ProcessorAbstract {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessorImpl.class);

    @Autowired
    private QueuePaymentImpl queuePayment;

    @Autowired
    private MachineProcessorImpl machineProcessor;

    public void callPayCoffee(Employees.Employee employee, String name, Payment payment, List<PostAction> postActions, QueueCallable parentCallable) {
        final PaymentCallableImpl paymentCallable = new PaymentCallableImpl(
                employee,
                name,
                payment,
                postActions,
                machineProcessor
        );
        parentCallable.getAllCallables().add(paymentCallable);
    }

    @Override
    public Queue getExecutorServiceQueue() {
        return queuePayment;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((PaymentCallableImpl) callable).getName();
    }

    public void addQueueSize(int queueSize, String name) {
        queuePayment.setQueueSize(queueSize, name);
    }

    public void initExecutors() {
        queuePayment.initExecutors();
    }

    public void stopExectutors() {
        queuePayment.stopExecutors();
    }

    public abstract void waitForAllCalls(QueueCallable queueCallable);

    public abstract void runAllCalls(QueueCallable queueCallable);
}

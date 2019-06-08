package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.concurrency.PaymentCallableImpl;
import com.jesperancinha.coffee.system.concurrency.QueueCallable;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.queues.QueueAbstract;
import com.jesperancinha.coffee.system.queues.QueuePaymentImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
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
public class PaymentProcessorImpl extends ProcessorAbstract implements PaymentProcessor {

    private static final Logger logger = Logger.getLogger(PaymentProcessorImpl.class);

    @Autowired
    private QueuePaymentImpl queuePayment;

    @Autowired
    private MachineProcessor machineProcessor;

    @Override
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
    public QueueAbstract getExecutorServiceQueue() {
        return queuePayment;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((PaymentCallableImpl) callable).getName();
    }

    @Override
    public void addQueueSize(int queueSize, String name) {
        queuePayment.setQueueSize(queueSize, name);
    }

    @Override
    public void initExecutors() {
        queuePayment.initExecutors();
    }

    @Override
    public void stopExectutors() {
        queuePayment.stopExecutors();
    }
}

package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.PaymentCallableImpl;
import com.steelzack.coffee.system.concurrency.QueueCallable;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueuePaymentImpl;
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


    private Payment chosenPayment;
    private List<PostAction> postActions;

    @Override
    public void setChosenPayment(Payment chosenPayment, List<PostAction> postActions) {
        this.chosenPayment = chosenPayment;
        this.postActions = postActions;
    }

    @Override
    public void callPayCoffee(String name, QueueCallable parentCallable) {
        parentCallable.getAllCallables().add(new PaymentCallableImpl(chosenPayment, name));
    }

    @Override
    public QueueAbstract getExecutorServiceQueue() {
        return queuePayment;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((PaymentCallableImpl)callable).getName();
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

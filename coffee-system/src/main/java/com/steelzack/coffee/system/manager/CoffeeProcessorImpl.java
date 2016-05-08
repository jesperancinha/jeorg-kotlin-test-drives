package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.CoffeeMainCallable;
import com.steelzack.coffee.system.concurrency.CoffeeMainCallableImpl;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueueCofeeImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by joao on 29-4-16.
 */
@Accessors(chain = true)
@Getter
@Service
public class CoffeeProcessorImpl extends ProcessorAbstract implements CoffeeProcessor {
    private static final Logger logger = Logger.getLogger(CoffeeProcessorImpl.class);

    @Autowired
    private QueueCofeeImpl queueCofee;

    @Autowired
    private MachineProcessor machineProcessor;

    @Override
    public void callMakeCoffee( //
            Employee employee, //
            String name, //
            Coffee coffee, //
            Payment payment, //
            List<PostAction> postActions //
    ) {
        final CoffeeMainCallable coffeCallable = new CoffeeMainCallableImpl(employee, name, coffee, payment, postActions, machineProcessor);
        addCallable(coffeCallable);
    }

    @Override
    public QueueAbstract getExecutorServiceQueue() {
        return queueCofee;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((CoffeeMainCallable) callable).getName();
    }

    @Override
    public void addQueueSize(int queueSize, String name) {
        queueCofee.setQueueSize(queueSize, name);
    }

    @Override
    public void initExecutors() {
        queueCofee.initExecutors();
    }

    @Override
    public void stopExectutors() {
        queueCofee.stopExecutors();
    }
}

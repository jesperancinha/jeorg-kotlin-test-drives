package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.system.concurrency.CoffeeMainCallableImpl;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import org.jesperancinha.coffee.system.input.Employees.Employee;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.queues.Queue;
import com.jesperancinha.coffee.system.queues.QueueCofeeImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Accessors(chain = true)
@Getter
@Service
public abstract class CoffeeProcessorImpl extends ProcessorAbstract {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeProcessorImpl.class);

    @Autowired
    private QueueCofeeImpl queueCofee;

    @Autowired
    private MachineProcessorImpl machineProcessor;

    public void callMakeCoffee(
            Employee employee,
            String name,
            Coffee coffee,
            Payment payment,
            List<PostAction> postActions,
            QueueCallable parentCallable
    ) {
        final CoffeeMainCallableImpl coffeCallable = new CoffeeMainCallableImpl(employee, name, coffee, payment, postActions, machineProcessor);
        parentCallable.getAllCallables().add(coffeCallable);
    }

    @Override
    public Queue getExecutorServiceQueue() {
        return queueCofee;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((CoffeeMainCallableImpl) callable).getName();
    }

    public void addQueueSize(int queueSize, String name) {
        queueCofee.setQueueSize(queueSize, name);
    }

    public void initExecutors() {
        queueCofee.initExecutors();
    }

    public void stopExectutors() {
        queueCofee.stopExecutors();
    }

    public abstract void waitForAllCalls(QueueCallable queueCallable);

    public abstract void runAllCalls(QueueCallable queueCallable);
}

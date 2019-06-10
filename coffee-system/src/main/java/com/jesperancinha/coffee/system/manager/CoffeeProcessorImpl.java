package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.api.concurrency.CoffeeMainCallable;
import com.jesperancinha.coffee.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.api.manager.CoffeeProcessor;
import com.jesperancinha.coffee.api.manager.MachineProcessor;
import com.jesperancinha.coffee.system.concurrency.CoffeeMainCallableImpl;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees.Employee;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
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
public class CoffeeProcessorImpl extends ProcessorAbstract implements CoffeeProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeProcessorImpl.class);

    @Autowired
    private QueueCofeeImpl queueCofee;

    @Autowired
    private MachineProcessor machineProcessor;

    @Override
    public void callMakeCoffee(
            Employee employee,
            String name,
            Coffee coffee,
            Payment payment,
            List<PostAction> postActions,
            QueueCallable parentCallable
    ) {
        final CoffeeMainCallable coffeCallable = new CoffeeMainCallableImpl(employee, name, coffee, payment, postActions, machineProcessor);
        parentCallable.getAllCallables().add(coffeCallable);
    }

    @Override
    public Queue getExecutorServiceQueue() {
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

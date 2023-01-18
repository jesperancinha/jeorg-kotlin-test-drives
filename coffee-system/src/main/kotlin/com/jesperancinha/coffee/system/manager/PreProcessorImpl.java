package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import com.jesperancinha.coffee.system.concurrency.ActionCallable;
import com.jesperancinha.coffee.system.concurrency.PreActionCallableImpl;
import com.jesperancinha.coffee.system.concurrency.StartupCallableImpl;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import org.jesperancinha.coffee.system.input.Employees.Employee;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.jesperancinha.coffee.system.queues.Queue;
import com.jesperancinha.coffee.system.queues.QueuePreActivityImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Accessors(chain = true)
@Getter
@Service
public abstract class PreProcessorImpl extends ProcessorAbstract {

    private final StartupCallableImpl startupCallable;
    @Autowired
    private QueuePreActivityImpl queuePreActivity;
    @Autowired
    private MachineProcessorImpl machineProcessor;

    PreProcessorImpl() {
        super();
        startupCallable = new StartupCallableImpl();
    }

    public void callPreActions(
            final Employee employee,
            final String name,
            final List<PreAction> actions,
            final Coffee coffee, Payment payment,
            final List<PostAction> postActions
    ) {

        final PreActionCallableImpl preActionCallable = new PreActionCallableImpl(
                employee,
                name,
                coffee,
                payment,
                postActions,
                machineProcessor
        );
        actions.forEach(
                preActionCallable::addPreAction
        );
        startupCallable.getAllCallables().add(preActionCallable);
    }

    public void runAllCalls() {
        runAllCalls(startupCallable);
    }

    public void waitForAllCalls() {
        waitForAllCalls(startupCallable);
    }

    @Override
    public Queue getExecutorServiceQueue() {
        return queuePreActivity;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((ActionCallable) callable).getName();
    }

    public void addQueueSize(int queueSize, String name) {
        queuePreActivity.setQueueSize(queueSize, name);
    }

    public void initExecutors() {
        queuePreActivity.initExecutors();
    }

    public void stopExectutors() {
        queuePreActivity.stopExecutors();
    }

    public abstract void waitForAllCalls(QueueCallable queueCallable);

    public abstract void runAllCalls(QueueCallable queueCallable);
}

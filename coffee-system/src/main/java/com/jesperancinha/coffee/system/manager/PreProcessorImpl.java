package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.api.concurrency.PreActionCallable;
import com.jesperancinha.coffee.api.concurrency.StartupCallable;
import com.jesperancinha.coffee.api.manager.MachineProcessor;
import com.jesperancinha.coffee.api.manager.PreProcessor;
import com.jesperancinha.coffee.system.concurrency.ActionCallable;
import com.jesperancinha.coffee.system.concurrency.PreActionCallableImpl;
import com.jesperancinha.coffee.system.concurrency.StartupCallableImpl;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees.Employee;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction;
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
public class PreProcessorImpl extends ProcessorAbstract implements PreProcessor {

    private final StartupCallable startupCallable;
    @Autowired
    private QueuePreActivityImpl queuePreActivity;
    @Autowired
    private MachineProcessor machineProcessor;

    PreProcessorImpl() {
        super();
        startupCallable = new StartupCallableImpl();
    }

    @Override
    public void callPreActions(
            final Employee employee,
            final String name,
            final List<PreAction> actions,
            final Coffee coffee, Payment payment,
            final List<PostAction> postActions
    ) {

        final PreActionCallable preActionCallable = new PreActionCallableImpl(
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

    @Override
    public void runAllCalls() {
        runAllCalls(startupCallable);
    }

    @Override
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

    @Override
    public void addQueueSize(int queueSize, String name) {
        queuePreActivity.setQueueSize(queueSize, name);
    }

    @Override
    public void initExecutors() {
        queuePreActivity.initExecutors();
    }

    @Override
    public void stopExectutors() {
        queuePreActivity.stopExecutors();
    }
}

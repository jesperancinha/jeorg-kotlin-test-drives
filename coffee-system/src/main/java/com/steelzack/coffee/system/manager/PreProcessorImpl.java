package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.ActionCallable;
import com.steelzack.coffee.system.concurrency.PreActionCallable;
import com.steelzack.coffee.system.concurrency.PreActionCallableImpl;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueuePreActivityImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Accessors(chain = true)
@Getter
@Service
public class PreProcessorImpl extends ProcessorAbstract implements PreProcessor {

    private static final Logger logger = Logger.getLogger(PreProcessorImpl.class);

    private List<Actions.PreAction> actions;
    private Coffee coffee;

    @Autowired
    private QueuePreActivityImpl queuePreActivity;

    @Override
    public void setActions(List<Actions.PreAction> actions, Coffee coffee) {
        this.actions = actions;
        this.coffee = coffee;
    }

    @Override
    public void callPreActions(final String name) {
        final PreActionCallable preActionCallable = new PreActionCallableImpl(name);
        allCallables.add(preActionCallable);
        actions.forEach( //
                preActionCallable::addPreAction //
        ); //
    }

    @Override
    public QueueAbstract getExecutorServiceQueue() {
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

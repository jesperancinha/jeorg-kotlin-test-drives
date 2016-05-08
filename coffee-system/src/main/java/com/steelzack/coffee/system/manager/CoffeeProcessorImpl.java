package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.CoffeeCallableImpl;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueueCofeeImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by joao on 29-4-16.
 */
@Accessors(chain = true)
@Getter
@Service
public class CoffeeProcessorImpl extends ProcessorAbstract implements CoffeeProcessor {
    private static final Logger logger = Logger.getLogger(CoffeeProcessorImpl.class);

    private Coffee chosenCoffee;
    private Payment payment;

    @Autowired
    private QueueCofeeImpl queueCofee;

    @Autowired
    private MachineProcessor machineProcessor;

    @Override
    public void setChosenCoffee(Coffee chosenCoffee, Payment payment, List<Employees.Employee.Actions.PostAction> postActions) {
        this.chosenCoffee = chosenCoffee;
        this.payment = payment;
    }

    @Override
    public void callMakeCoffee(String name) {
        final List<Coffee.TimesToFill.FillTime> tasks = chosenCoffee.getTimesToFill().getFillTime();
        final Set<Integer> allIndexes = new HashSet<>();
        tasks.stream().sorted( //
                (fillTime1, fillTime2) -> fillTime1.getIndex().compareTo(fillTime2.getIndex()) //
        ).map(
                Coffee.TimesToFill.FillTime::getIndex //
        ).forEach(
                index -> allIndexes.add(index.intValue()) //
        );

        for (Integer index : allIndexes) { //
            final List<Coffee.TimesToFill.FillTime> allTasksForIndex = tasks.stream().filter( //
                    fillTime -> fillTime.getIndex().intValue() == index //
            ).collect(Collectors.toList()); //

            allTasksForIndex.stream().forEach( //
                    fillTime -> //
                            allCallables.add(new CoffeeCallableImpl(fillTime, name))
            );
        }
    }

    @Override
    public QueueAbstract getExecutorServiceQueue() {
        return queueCofee;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((CoffeeCallableImpl) callable).getName();
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

package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.sun.javafx.binding.StringFormatter;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.enterprise.concurrent.ManagedExecutorService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Getter
@Service

public class EmployeeCallableImpl implements Employee, Callable<Boolean> {
    public static final String SCHEDULED_TASK_FAILD_TO_EXECUTE = "scheduled task faild to execute!";
    private final Logger logger = Logger.getLogger(EmployeeCallableImpl.class);

    private Actions actions;
    private Employees.Employee employee;
    private Coffee chosenCoffee;
    private Payment chosenPayment;

    @Autowired
    private ManagedExecutorService managedExecutorService;

    public EmployeeCallableImpl() {
    }

    public EmployeeCallableImpl(Actions actions, Employees.Employee employee, Coffee chosenCoffee, Payment chosenPayment) {
        this.actions = actions;
        this.employee = employee;
        this.chosenCoffee = chosenCoffee;
        this.chosenPayment = chosenPayment;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(StringFormatter.format("Employee {0} is waiting in line", employee.getName()));
        callPreActions();
        callMakeCoffee();
        callPayCoffee();
        callPostActions();
        return true;
    }

    private void callPayCoffee() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            if (!executorService.submit(new PaymentCallableImpl(chosenPayment)).get()) {
                logger.error(SCHEDULED_TASK_FAILD_TO_EXECUTE);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void callMakeCoffee() throws InterruptedException {
        final List<FillTime> tasks = chosenCoffee.getTimesToFill().getFillTime();
        final Set<Integer> allIndexes = new HashSet<>();
        final Set<Future<Boolean>> allResults = new HashSet<>();
        tasks.stream().sorted( //
                (fillTime1, fillTime2) -> fillTime1.getIndex().compareTo(fillTime2.getIndex()) //
        ).map(
                FillTime::getIndex //
        ).forEach(
                index -> allIndexes.add(index.intValue()) //
        );

        for (Integer index : allIndexes) { //
            final List<FillTime> allTasksForIndex = tasks.stream().filter( //
                    fillTime -> fillTime.getIndex().intValue() == index //
            ).collect(Collectors.toList()); //

            allTasksForIndex.stream().forEach( //
                    fillTime -> //
                    {
                        final CoffeeCallableImpl coffeeCallable = new CoffeeCallableImpl(fillTime);
                        final Future<Boolean> future = managedExecutorService.submit(coffeeCallable);
                        allResults.add(future);
                    }
            );
            allResults.stream().forEach( //
                    booleanFuture -> { //
                        try { //
                            if (!booleanFuture.get()) { //
                                logger.error(SCHEDULED_TASK_FAILD_TO_EXECUTE); //
                            }
                        } catch (InterruptedException | ExecutionException e) { //
                            logger.error(e.getMessage(), e); //
                        }
                    }
            );
        }
    }

    private void callPostActions() {
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        postActions.stream().forEach( //
                postAction -> { //
                    try {
                        if (!managedExecutorService.submit(new ActionCallableImpl(postAction)).get()) {
                            logger.error(SCHEDULED_TASK_FAILD_TO_EXECUTE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                } //
        ); //
    }

    private void callPreActions() {
        final List<Actions.PreAction> preActions = this.actions.getPreAction();
        preActions.stream().forEach( //
                preAction -> { //
                    try {
                        if (!managedExecutorService.submit(new ActionCallableImpl(preAction)).get()) {
                            logger.error(SCHEDULED_TASK_FAILD_TO_EXECUTE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                } //
        ); //
    }
}

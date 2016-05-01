package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.sun.javafx.binding.StringFormatter;
import lombok.Builder;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Builder
@Getter
public class EmployeeCallableImpl implements Employee, Callable<Boolean> {
    private final Logger logger = Logger.getLogger(EmployeeCallableImpl.class);

    private final Actions actions;
    private final Employees.Employee employee;
    private final Coffee chosenCoffee;
    private final Payment chosenPayment;

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

    }

    private void callMakeCoffee() throws InterruptedException {
        final List<FillTime> tasks = chosenCoffee.getTimesToFill().getFillTime();
        final Set<Integer> allIndexes = new HashSet<>();
        final Set<Future<Boolean>> allResults = new HashSet<>();
        tasks.stream().sorted( //
                (fillTime1, fillTime2) -> fillTime1.getIndex().compareTo(fillTime2.getIndex())
        ).map(
                FillTime::getIndex
        ).forEach(
                index -> allIndexes.add(index.intValue())
        );

        for (Integer index : allIndexes) {
            final List<FillTime> allTasksForIndex = tasks.stream().filter(
                    fillTime -> fillTime.getIndex().intValue() == index
            ).collect(Collectors.toList());
            final ExecutorService executorService = Executors.newFixedThreadPool(allIndexes.size());
            allTasksForIndex.stream().forEach(
                    fillTime ->
                    {
                        CoffeeCallableImpl coffeeCallable = new CoffeeCallableImpl(fillTime);

                        Future<Boolean> future = executorService.submit(coffeeCallable);
                        allResults.add(future);
                    }
            );
            allResults.stream().forEach(
                    booleanFuture -> {
                        try {
                            if (!booleanFuture.get().booleanValue()) ;
                            {
                                logger.error("scheduled task faild to execute!");
                            }
                        } catch (InterruptedException e) {
                            logger.error(e.getMessage(), e);
                        } catch (ExecutionException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
            );
        }


//                .forEach( //
//                fillTime -> { //
//
//                }
//        );
    }

    private void callPostActions() {
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        postActions.stream().forEach( //
                postAction -> { //

                } //
        ); //
    }

    private void callPreActions() {
        final List<Actions.PreAction> preActions = this.actions.getPreAction();
        preActions.stream().forEach( //
                preAction -> { //

                } //
        ); //
    }
}

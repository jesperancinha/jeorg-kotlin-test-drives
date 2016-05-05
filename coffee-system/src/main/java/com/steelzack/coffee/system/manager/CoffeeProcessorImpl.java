package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.CoffeeCallableImpl;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.steelzack.coffee.system.concurrency.EmployeeCallableImpl.SCHEDULED_TASK_FAILED_TO_EXECUTE;

/**
 * Created by joao on 29-4-16.
 */
@Accessors(chain = true)
@Builder
@Getter
@Service
public class CoffeeProcessorImpl extends ProcessorImpl implements CoffeeProcessor{
    private static final Logger logger = Logger.getLogger(CoffeeProcessorImpl.class);

    private  Coffee chosenCoffee;

    @Override
    public void setChosenCoffee(Coffee chosenCoffee) {
        this.chosenCoffee = chosenCoffee;
    }

    @Override
    public void callMakeCoffee() {
        final List<Coffee.TimesToFill.FillTime> tasks = chosenCoffee.getTimesToFill().getFillTime();
        final Set<Integer> allIndexes = new HashSet<>();
        final Set<Future<Boolean>> allResults = new HashSet<>();
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
                                logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE); //
                            }
                        } catch (InterruptedException | ExecutionException e) { //
                            logger.error(e.getMessage(), e); //
                        }
                    }
            );
            allResults.clear();
        }
    }

}
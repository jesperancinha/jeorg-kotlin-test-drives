package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.manager.MachineProcessor;
import com.steelzack.coffee.system.manager.PaymentProcessor;
import com.steelzack.coffee.system.utils.ExecutorServiceHelper;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.steelzack.coffee.system.manager.ProcessorAbstract.SCHEDULED_TASK_FAILED_TO_EXECUTE;

/**
 * Created by joao on 7-5-16.
 */
@Getter
public class CoffeeMainCallableImpl extends QueueCallableAbstract implements CoffeeMainCallable {
    private static final Logger logger = Logger.getLogger(CoffeeMainCallableImpl.class);

    private Employee employee;
    private String name;
    private Coffee coffee;
    private Payment payment;
    private List<PostAction> postActions;
    private MachineProcessor machineProcessor;

    public CoffeeMainCallableImpl(Employee employee, String name, Coffee coffee, Payment payment, List<PostAction> postActions, MachineProcessor machineProcessor) {
        this.employee = employee;
        this.name = name;
        this.coffee = coffee;
        this.payment = payment;
        this.postActions = postActions;
        this.machineProcessor = machineProcessor;
    }

    @Override
    public Boolean call() throws Exception {
        final List<FillTime> tasks = this.coffee.getTimesToFill().getFillTime();
        final Set<Integer> allIndexes = new HashSet<>();
        tasks.stream().sorted( //
                (fillTime1, fillTime2) -> fillTime1.getIndex().compareTo(fillTime2.getIndex()) //
        ).map(
                FillTime::getIndex //
        ).forEach(
                allIndexes::add //
        );

        allIndexes.stream().forEach(
                index -> {
                    final List<Future<Boolean>> allCoffeeCallables = new ArrayList<>();
                    final List<FillTime> allTasksForIndex = tasks.stream().filter( //
                            fillTime -> fillTime.getIndex().intValue() == index //
                    ).collect(Collectors.toList()); //

                    final ExecutorService executor = Executors.newFixedThreadPool(allTasksForIndex.size());
                    allTasksForIndex.stream().forEach( //
                            fillTime -> //
                                    allCoffeeCallables.add(executor.submit(new CoffeeCallableImpl(fillTime, name)))
                    );

                    allCoffeeCallables.stream().forEach(
                            booleanFuture -> {
                                try { //
                                    if (booleanFuture.get() != null && !booleanFuture.get()) { //
                                        logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE); //
                                    }
                                } catch (NullPointerException | InterruptedException | ExecutionException e) { //
                                    logger.error(e.getMessage(), e); //
                                }
                            }
                    );

                    ExecutorServiceHelper.shutDownExecutorService(executor);
                }


        );

        final PaymentProcessor paymentProcessor = machineProcessor.getPaymentProcessor();
        machineProcessor.callPayCoffee(employee, payment.getName(), payment, postActions, this);
        paymentProcessor.runAllCalls(this);
        paymentProcessor.waitForAllCalls(this);
        paymentProcessor.stopExectutors();
        return true;
    }

}

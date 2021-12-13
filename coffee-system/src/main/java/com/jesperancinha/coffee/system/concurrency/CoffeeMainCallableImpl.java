package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.api.concurrency.CoffeeMainCallable;
import com.jesperancinha.coffee.api.manager.MachineProcessor;
import com.jesperancinha.coffee.api.manager.PaymentProcessor;
import com.jesperancinha.coffee.api.utils.ExecutorServiceHelper;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees.Employee;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Getter
@Slf4j
public class CoffeeMainCallableImpl extends QueueCallableAbstract implements CoffeeMainCallable {

    private final Employee employee;
    private final String name;
    private final Coffee coffee;
    private final Payment payment;
    private final List<PostAction> postActions;
    private final MachineProcessor machineProcessor;

    public CoffeeMainCallableImpl(Employee employee, String name, Coffee coffee, Payment payment,
                                  List<PostAction> postActions, MachineProcessor machineProcessor) {
        this.employee = employee;
        this.name = name;
        this.coffee = coffee;
        this.payment = payment;
        this.postActions = postActions;
        this.machineProcessor = machineProcessor;
    }

    @Override
    public Boolean call() {
        final List<FillTime> tasks = this.coffee.getTimesToFill().getFillTime();
        final Set<Integer> allIndexes = new HashSet<>();
        tasks.stream().sorted(Comparator.comparing(FillTime::getIndex))
                .map(FillTime::getIndex).forEach(allIndexes::add);

        allIndexes.forEach(
                index -> {
                    final List<Future<Boolean>> allCoffeeCallables = new ArrayList<>();
                    final List<FillTime> allTasksForIndex = tasks.stream().filter(
                            fillTime -> fillTime.getIndex().intValue() == index
                    ).toList();

                    final ExecutorService executor = Executors.newFixedThreadPool(allTasksForIndex.size());
                    allTasksForIndex.forEach(
                            fillTime ->
                                    allCoffeeCallables.add(executor.submit(new CoffeeCallableImpl(fillTime, name)))
                    );

                    waitForAllFutures(allCoffeeCallables, log);

                    ExecutorServiceHelper.shutDownExecutorService(executor);
                }

        );

        final PaymentProcessor paymentProcessor = machineProcessor.getPaymentProcessor();
        machineProcessor.callPayCoffee(employee, payment.getName(), payment, postActions, this);
        paymentProcessor.runAllCalls(this);
        paymentProcessor.waitForAllCalls(this);
        return true;
    }

}

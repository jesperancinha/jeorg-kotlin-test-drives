package com.jesperancinha.coffee.system.concurrency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.jesperancinha.coffee.system.input.Employees.Employee;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.manager.MachineProcessor;
import com.jesperancinha.coffee.system.manager.PaymentProcessor;
import com.jesperancinha.coffee.system.utils.ExecutorServiceHelper;
import lombok.Getter;
import org.apache.log4j.Logger;

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

					waitForAllFutures(allCoffeeCallables, logger);

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

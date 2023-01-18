package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.api.utils.ExecutorServiceHelper
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import lombok.Getter
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate

@Getter
@Slf4j
class CoffeeMainCallableImpl(
    employee: Employee?, name: String?, coffee: Coffee, payment: Payment,
    postActions: List<PostAction?>, machineProcessor: MachineProcessorImpl?
) : QueueCallableAbstract(), QueueCallable {
    private val employee: Employee?
    private val name: String?
    private val coffee: Coffee
    private val payment: Payment
    private val postActions: List<PostAction?>
    private val machineProcessor: MachineProcessorImpl?

    init {
        this.employee = employee
        this.name = name
        this.coffee = coffee
        this.payment = payment
        this.postActions = postActions
        this.machineProcessor = machineProcessor
    }

    override fun call(): Boolean {
        val tasks: List<FillTime> = coffee.getTimesToFill().getFillTime()
        val allIndexes: MutableSet<Int> = HashSet()
        tasks.stream()
            .sorted(Comparator.comparing<FillTime, Int>(Function<FillTime, Int> { obj: FillTime -> obj.getIndex() }))
            .map(Function<FillTime, Int> { obj: FillTime -> obj.getIndex() }).forEach { e: Int -> allIndexes.add(e) }
        allIndexes.forEach(
            Consumer { index: Int ->
                val allCoffeeCallables: MutableList<Future<Boolean?>> = ArrayList()
                val allTasksForIndex: List<FillTime> = tasks.stream().filter(
                    Predicate<FillTime> { fillTime: FillTime -> fillTime.getIndex() == index }
                ).toList()
                val executor = Executors.newFixedThreadPool(allTasksForIndex.size)
                allTasksForIndex.forEach(
                    Consumer<FillTime> { fillTime: FillTime ->
                        allCoffeeCallables.add(
                            executor.submit(
                                CoffeeCallableImpl(
                                    fillTime,
                                    name
                                )
                            )
                        )
                    }
                )
                waitForAllFutures(allCoffeeCallables, CoffeeMainCallableImpl.log)
                ExecutorServiceHelper.shutDownExecutorService(executor)
            }
        )
        val paymentProcessor = machineProcessor.getPaymentProcessor()
        machineProcessor!!.callPayCoffee(employee, payment.getName(), payment, postActions, this)
        paymentProcessor.runAllCalls(this)
        paymentProcessor.waitForAllCalls(this)
        return true
    }
}
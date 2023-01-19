package org.jesperancinha.coffee.system.concurrency

import lombok.Getter
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.api.utils.ExecutorServiceHelper
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.util.concurrent.Executors
import java.util.concurrent.Future

@Getter
@Slf4j
class CoffeeMainCallableImpl(
    employee: Employee,
    name: String,
    coffee: Coffee,
    payment: Payment,
    postActions: List<PostAction>,
    machineProcessor: MachineProcessorImpl
) : QueueCallableAbstract(), QueueCallable {
    private val employee: Employee
    val name: String
    private val coffee: Coffee
    private val payment: Payment
    private val postActions: List<PostAction>
    private val machineProcessor: MachineProcessorImpl

    init {
        this.employee = employee
        this.name = name
        this.coffee = coffee
        this.payment = payment
        this.postActions = postActions
        this.machineProcessor = machineProcessor
    }

    override fun call(): Boolean {
        val tasks: List<FillTime> = coffee.timesToFill.fillTime
        val allIndexes: MutableSet<Int> = HashSet()
        tasks.sortedBy(FillTime::getIndex)
            .map(FillTime::getIndex).forEach { e: Int -> allIndexes.add(e) }
        allIndexes.forEach{ index: Int ->
                val allCoffeeCallables: MutableList<Future<Boolean>> = ArrayList()
                val allTasksForIndex: List<FillTime> = tasks.stream().filter { fillTime: FillTime -> fillTime.index == index }
                    .toList()
                val executor = Executors.newFixedThreadPool(allTasksForIndex.size)
                allTasksForIndex.forEach{ fillTime: FillTime ->
                        allCoffeeCallables.add(
                            executor.submit(
                                CoffeeCallableImpl(
                                    fillTime,
                                    name
                                )
                            )
                        )
                    }
                waitForAllFutures(allCoffeeCallables, logger)
                ExecutorServiceHelper.shutDownExecutorService(executor)
            }
        val paymentProcessor = machineProcessor.paymentProcessor
        machineProcessor.callPayCoffee(employee, payment.name, payment, postActions, this)
        paymentProcessor.runAllCalls(this)
        paymentProcessor.waitForAllCalls(this)
        return true
    }

    companion object{
        val logger: Logger = getLogger(CoffeeMainCallableImpl::class.java)
    }
}
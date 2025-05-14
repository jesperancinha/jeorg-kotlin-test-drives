package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.api.utils.ExecutorServiceHelper
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.PaymentProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.util.concurrent.Executors
import java.util.concurrent.Future

class CoffeeMainCallable(
    val employee: Employee,
    val name: String,
    val coffee: Coffee,
    val payment: Payment,
    private val postActions: List<PostAction>,
    private val paymentProcessor: PaymentProcessor
) : QueueCallableAbstract(), QueueCallable {


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
                                CoffeeCallable(
                                    fillTime,
                                    name
                                )
                            )
                        )
                    }
                waitForAllFutures(allCoffeeCallables, logger)
                ExecutorServiceHelper.shutDownExecutorService(executor)
            }
        paymentProcessor.callPayCoffee(employee, payment.name, payment, postActions, this)
        paymentProcessor.runAllCalls(this)
        paymentProcessor.waitForAllCalls(this)
        return true
    }

    companion object{
        val logger: Logger = getLogger(CoffeeMainCallable::class.java)
    }
}
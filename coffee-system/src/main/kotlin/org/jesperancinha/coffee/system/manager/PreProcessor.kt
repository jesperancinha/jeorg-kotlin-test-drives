package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.concurrency.ActionCallable
import org.jesperancinha.coffee.system.concurrency.PreActionCallable
import org.jesperancinha.coffee.system.concurrency.StartupCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.QueuePreActivityImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable

@Service
class PreProcessor(
    @Autowired
    val queuePreActivity: QueuePreActivityImpl,
    @Autowired
    val coffeeProcessor: CoffeeProcessor
): ProcessorAbstract() {
    private val startupCallable: StartupCallable by lazy { StartupCallable() }

    fun callPreActions(
        employee: Employee,
        name: String,
        actions: List<Employee.Actions.PreAction>,
        coffee: Coffee,
        payment: Payment,
        postActions: List<PostAction>
    ) {

        val preActionCallable = PreActionCallable(
            coffeeProcessor,
            employee = employee,
            name = name,
            coffee = coffee,
            payment = payment,
            postActions = postActions
        )
        actions.forEach { preAction ->
            preActionCallable.addPreAction(
                preAction
            )
        }
        startupCallable.allCallables.add(preActionCallable)
    }

    fun runAllCalls() = runAllCalls(startupCallable)

    fun waitForAllCalls() = waitForAllCalls(startupCallable)

    override val executorServiceQueue = queuePreActivity

    override fun getExecutorName(callable: Callable<Boolean>) = requireNotNull((callable as ActionCallable).name)

    fun addQueueSize(queueSize: Int, name: String) = queuePreActivity.setQueueSize(queueSize, name)

    fun initExecutors() = queuePreActivity.initExecutors()

    fun stopExecutors() = queuePreActivity.stopExecutors()
}
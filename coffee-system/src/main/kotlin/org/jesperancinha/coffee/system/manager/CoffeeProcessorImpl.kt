package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.CoffeeMainCallableImpl
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.Queue
import org.jesperancinha.coffee.system.queues.QueueCofeeImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable

@Service
class CoffeeProcessorImpl(
    @Autowired
    private val queueCofee: QueueCofeeImpl,
    @Autowired
    private val machineProcessor: MachineProcessor
) : ProcessorAbstract() {

    fun callMakeCoffee(
        employee: Employee,
        name: String,
        coffee: Coffee,
        payment: Payment,
        postActions: List<PostAction>,
        parentCallable: QueueCallable?
    ) {
        val coffeeMainCallableImpl = CoffeeMainCallableImpl(employee, name, coffee, payment, postActions, machineProcessor)
        parentCallable?.allCallables?.add(coffeeMainCallableImpl)
    }

    override val executorServiceQueue: Queue = queueCofee

    override fun getExecutorName(callable: Callable<Boolean>): String = (callable as CoffeeMainCallableImpl).name

    fun addQueueSize(queueSize: Int, name: String) {
        queueCofee.setQueueSize(queueSize, name)
    }

    fun initExecutors() {
        queueCofee.initExecutors()
    }

    fun stopExecutors() {
        queueCofee.stopExecutors()
    }
}
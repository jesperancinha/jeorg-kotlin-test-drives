package org.jesperancinha.coffee.system.manager

import lombok.Getter
import lombok.experimental.Accessors
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.ActionCallable
import org.jesperancinha.coffee.system.concurrency.PreActionCallableImpl
import org.jesperancinha.coffee.system.concurrency.StartupCallableImpl
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.Queue
import org.jesperancinha.coffee.system.queues.QueuePreActivityImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable
import java.util.function.Consumer

@Accessors(chain = true)
@Getter
@Service
abstract class PreProcessorImpl internal constructor() : ProcessorAbstract() {
    private val startupCallable: StartupCallableImpl

    @Autowired
    private val queuePreActivity: QueuePreActivityImpl? = null

    @Autowired
    private val machineProcessor: MachineProcessorImpl? = null

    init {
        startupCallable = StartupCallableImpl()
    }

    fun callPreActions(
        employee: Employee,
        name: String?,
        actions: List<org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction?>,
        coffee: Coffee, payment: Payment,
        postActions: List<PostAction?>
    ) {
        val preActionCallable = PreActionCallableImpl(
            employee,
            name,
            coffee,
            payment,
            postActions,
            machineProcessor
        )
        actions.forEach(Consumer<org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction> { preAction: org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction ->
            preActionCallable.addPreAction(
                preAction
            )
        })
        startupCallable.allCallables.add(preActionCallable)
    }

    fun runAllCalls() {
        runAllCalls(startupCallable)
    }

    fun waitForAllCalls() {
        waitForAllCalls(startupCallable)
    }

    override val executorServiceQueue: Queue?
        get() = queuePreActivity

    override fun getExecutorName(callable: Callable<Boolean?>): String {
        return (callable as ActionCallable).name
    }

    fun addQueueSize(queueSize: Int, name: String?) {
        queuePreActivity!!.setQueueSize(queueSize, name!!)
    }

    fun initExecutors() {
        queuePreActivity!!.initExecutors()
    }

    fun stopExectutors() {
        queuePreActivity!!.stopExecutors()
    }

    abstract override fun waitForAllCalls(queueCallable: QueueCallable)
    abstract override fun runAllCalls(queueCallable: QueueCallable)
}
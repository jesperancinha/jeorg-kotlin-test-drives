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
import org.jesperancinha.coffee.system.queues.QueuePreActivityImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable

@Accessors(chain = true)
@Getter
@Service
abstract class PreProcessorImpl internal constructor() : ProcessorAbstract() {
    private val startupCallable: StartupCallableImpl by lazy { StartupCallableImpl() }

    @Autowired
    lateinit var queuePreActivity: QueuePreActivityImpl

    @Autowired
    lateinit var machineProcessor: MachineProcessorImpl

    fun callPreActions(
        employee: Employee,
        name: String,
        actions: List<Employee.Actions.PreAction>,
        coffee: Coffee,
        payment: Payment,
        postActions: List<PostAction>
    ) {
        val preActionCallable = PreActionCallableImpl(
            employee,
            name,
            coffee,
            payment,
            postActions,
            machineProcessor
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

    override fun getExecutorName(callable: Callable<Boolean>) = (callable as ActionCallable).name

    fun addQueueSize(queueSize: Int, name: String) = queuePreActivity.setQueueSize(queueSize, name)

    fun initExecutors() = queuePreActivity.initExecutors()

    fun stopExecutors() = queuePreActivity.stopExecutors()

    abstract override fun waitForAllCalls(queueCallable: QueueCallable)
    abstract override fun runAllCalls(queueCallable: QueueCallable)
}
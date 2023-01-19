package org.jesperancinha.coffee.system.manager

import lombok.experimental.Accessors
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.PaymentCallableImpl
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.Queue
import org.jesperancinha.coffee.system.queues.QueuePaymentImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Service
abstract class PaymentProcessorImpl(
    @Autowired
    private val queuePayment: QueuePaymentImpl,
    @Autowired
    private val machineProcessor: MachineProcessorImpl
) : ProcessorAbstract() {

    fun callPayCoffee(
        employee: Employee,
        name: String,
        payment: Payment,
        postActions: List<PostAction>,
        parentCallable: QueueCallable
    ) {
        val paymentCallable = PaymentCallableImpl(
            employee,
            name,
            payment,
            postActions,
            machineProcessor
        )
        parentCallable.allCallables.add(paymentCallable)
    }

    override val executorServiceQueue: Queue = queuePayment

    override fun getExecutorName(callable: Callable<Boolean>): String =
        (callable as PaymentCallableImpl).name ?: throw RuntimeException("Executor not found!")

    fun addQueueSize(queueSize: Int, name: String) {
        queuePayment.setQueueSize(queueSize, name)
    }

    fun initExecutors() {
        queuePayment.initExecutors()
    }

    fun stopExectutors() {
        queuePayment.stopExecutors()
    }

    abstract override fun waitForAllCalls(queueCallable: QueueCallable)
    abstract override fun runAllCalls(queueCallable: QueueCallable)

    companion object {
        private val logger = LoggerFactory.getLogger(PaymentProcessorImpl::class.java)
    }
}
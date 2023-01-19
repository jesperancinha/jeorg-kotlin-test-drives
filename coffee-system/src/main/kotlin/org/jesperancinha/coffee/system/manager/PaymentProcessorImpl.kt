package org.jesperancinha.coffee.system.manager

import lombok.Getter
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
@Getter
@Service
abstract class PaymentProcessorImpl : ProcessorAbstract() {
    @Autowired
    private val queuePayment: QueuePaymentImpl? = null

    @Autowired
    private val machineProcessor: MachineProcessorImpl? = null
    fun callPayCoffee(
        employee: Employee?,
        name: String?,
        payment: Payment,
        postActions: List<PostAction?>,
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

    override val executorServiceQueue: Queue?
        get() = queuePayment

    override fun getExecutorName(callable: Callable<Boolean?>): String = (callable as PaymentCallableImpl).name ?: throw RuntimeException("Executor not found!")

    fun addQueueSize(queueSize: Int, name: String?) {
        queuePayment!!.setQueueSize(queueSize, name!!)
    }

    fun initExecutors() {
        queuePayment!!.initExecutors()
    }

    fun stopExectutors() {
        queuePayment!!.stopExecutors()
    }

    abstract override fun waitForAllCalls(queueCallable: QueueCallable)
    abstract override fun runAllCalls(queueCallable: QueueCallable)

    companion object {
        private val logger = LoggerFactory.getLogger(PaymentProcessorImpl::class.java)
    }
}
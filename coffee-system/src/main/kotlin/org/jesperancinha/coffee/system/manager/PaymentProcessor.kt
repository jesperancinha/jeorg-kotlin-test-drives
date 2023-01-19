package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.PaymentCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.Queue
import org.jesperancinha.coffee.system.queues.QueuePaymentImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Service
class PaymentProcessor(
    @Autowired
    private val queuePayment: QueuePaymentImpl,
    @Autowired
    private val postProcessor: PostProcessor

) : ProcessorAbstract() {

    fun callPayCoffee(
        employee: Employee,
        name: String,
        payment: Payment,
        postActions: List<PostAction>,
        parentCallable: QueueCallable
    ) {
        val paymentCallable = PaymentCallable(
            postProcessor = postProcessor,
            employee = employee,
            name = name,
            chosenPayment = payment,
            postActions = postActions
        )
        parentCallable.allCallables.add(paymentCallable)
    }

    override val executorServiceQueue: Queue = queuePayment

    override fun getExecutorName(callable: Callable<Boolean>): String = requireNotNull((callable as PaymentCallable).name)

    fun addQueueSize(queueSize: Int, name: String) {
        queuePayment.setQueueSize(queueSize, name)
    }

    fun initExecutors() {
        queuePayment.initExecutors()
    }

    fun stopExecutors() {
        queuePayment.stopExecutors()
    }
}
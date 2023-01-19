package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.GeneralProcessorImpl
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.slf4j.Logger
import java.text.MessageFormat
import java.util.concurrent.*

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
class PaymentCallableImpl(
    employee: Employee,
    name: String?,
    payment: Payment,
    postActions: List<PostAction>,
    private val machineProcessor: MachineProcessorImpl
) : QueueCallableAbstract(), QueueCallable {
    private val chosenPayment: Payment
    private val employee: Employee
    val name: String?
    private val postActions: List<PostAction>

    init {
        this.employee = employee
        chosenPayment = payment
        this.name = name
        this.postActions = postActions
    }

    @Throws(Exception::class)
    override fun call(): Boolean {
        logger.info(MessageFormat.format("PaymentCallable with {0}", chosenPayment.name))
        val time: Int = chosenPayment.time
        TimeUnit.MILLISECONDS.sleep(time.toLong())
        val postProcessor = machineProcessor.postProcessor
        machineProcessor.callPostActions(employee, GeneralProcessorImpl.Companion.MAIN_QUEUE_POST, postActions, this)
        postProcessor.runAllCalls(this)
        postProcessor.waitForAllCalls(this)
        return true
    }

    companion object {
        val logger: Logger = org.slf4j.LoggerFactory.getLogger(PaymentCallableImpl::class.java)
    }
}
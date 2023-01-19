package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.GeneralProcessorImpl
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.*

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Service
class PaymentCallableImpl(
    @Autowired
    private val machineProcessor: MachineProcessorImpl
) : QueueCallableAbstract(), QueueCallable {
    private var chosenPayment: Payment?=null
    private var employee: Employee?=null
    var name: String? = null
    private var postActions: List<PostAction>? = null

    fun init(
        employee: Employee,
        name: String?,
        payment: Payment,
        postActions: List<PostAction>,
    ) {
        this.employee = employee
        this.chosenPayment = payment
        this.name = name
        this.postActions = postActions
    }

    @Throws(Exception::class)
    override fun call(): Boolean {
        logger.info("PaymentCallable with {}", chosenPayment?.name)
        chosenPayment?.time?.let {
            time->
            TimeUnit.MILLISECONDS.sleep(time.toLong())
            val postProcessor = machineProcessor.postProcessor
            postActions?.let { machineProcessor.callPostActions(GeneralProcessorImpl.MAIN_QUEUE_POST, it, this) }
            postProcessor.runAllCalls(this)
            postProcessor.waitForAllCalls(this)
        }

        return true
    }

    companion object {
        val logger: Logger = org.slf4j.LoggerFactory.getLogger(PaymentCallableImpl::class.java)
    }
}
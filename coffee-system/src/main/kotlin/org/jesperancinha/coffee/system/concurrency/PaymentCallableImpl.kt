package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.manager.GeneralProcessorImpl
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import lombok.Getter
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.*

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Service
@Getter
@Slf4j
class PaymentCallableImpl(
    employee: Employee?,
    name: String?,
    payment: Payment,
    postActions: List<PostAction?>,
    machineProcessor: MachineProcessorImpl?
) : QueueCallableAbstract(), QueueCallable {
    private val chosenPayment: Payment

    @Autowired
    private val machineProcessor: MachineProcessorImpl?
    private val employee: Employee?
    val name: String?
    private val postActions: List<PostAction?>

    init {
        this.employee = employee
        chosenPayment = payment
        this.name = name
        this.postActions = postActions
        this.machineProcessor = machineProcessor
    }

    @Throws(Exception::class)
    override fun call(): Boolean {
        PaymentCallableImpl.log.info(MessageFormat.format("PaymentCallable with {0}", chosenPayment.getName()))
        val time: Int = chosenPayment.getTime()
        if (time != null) {
            TimeUnit.MILLISECONDS.sleep(time.toLong())
        }
        val postProcessor = machineProcessor.getPostProcessor()
        machineProcessor!!.callPostActions(employee, GeneralProcessorImpl.Companion.MAIN_QUEUE_POST, postActions, this)
        postProcessor.runAllCalls(this)
        postProcessor.waitForAllCalls(this)
        return true
    }
}
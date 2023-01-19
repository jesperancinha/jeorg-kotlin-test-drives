package org.jesperancinha.coffee.system.concurrency

import lombok.experimental.Accessors
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.jesperancinha.coffee.system.objects.ActionDescriptor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.*
import java.util.function.Consumer

@Service
@Accessors(chain = true)
class PreActionCallableImpl(
    employee: Employee,
    name: String,
    coffee: Coffee,
    payment: Payment,
    postActions: List<PostAction>,
    machineProcessor: MachineProcessorImpl
) : ActionCallable(name), QueueCallable {
    private val machineProcessor: MachineProcessorImpl
    private val coffee: Coffee
    private val payment: Payment
    private val postActions: List<PostAction>
    private val employee: Employee

    init {
        this.employee = employee
        this.name = name
        this.coffee = coffee
        this.payment = payment
        this.postActions = postActions
        this.machineProcessor = machineProcessor
    }

    fun addPreAction(preAction: Employee.Actions.PreAction) {
        actionDescriptorList.add(
            ActionDescriptor(
                description = preAction.description,
                time = preAction.time)
        )
    }

    override fun call(): Boolean {
        logger.info(MessageFormat.format("EmployeeCallable {0} is waiting in line", employee.name))
        actionDescriptorList.forEach(
            Consumer { actionDescriptor: ActionDescriptor ->
                logger.info(MessageFormat.format("Starting with {0}", actionDescriptor.description))
                try {
                    TimeUnit.MILLISECONDS.sleep(actionDescriptor.time.toLong())
                } catch (e: InterruptedException) {
                    logger.error(e.message, e)
                }
            }
        )
        val coffeeProcessor = machineProcessor.coffeeProcessor
        machineProcessor.callMakeCoffee(employee, coffee.name, coffee, payment, postActions, this)
        coffeeProcessor.runAllCalls(this)
        coffeeProcessor.waitForAllCalls(this)
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PreActionCallableImpl::class.java)
    }
}
package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.CoffeeProcessor
import org.jesperancinha.coffee.system.objects.ActionDescriptor
import org.slf4j.LoggerFactory
import java.text.MessageFormat
import java.util.concurrent.*
import java.util.function.Consumer

class PreActionCallable(
    private val coffeeProcessor: CoffeeProcessor,
    private val coffee: Coffee,
    private val payment: Payment,
    private val postActions: List<PostAction>,
    private val employee: Employee,
    name: String
) : ActionCallable(name), QueueCallable {

    fun addPreAction(preAction: Employee.Actions.PreAction) {
        actionDescriptorList.add(
            ActionDescriptor(
                description = preAction.description,
                time = preAction.time
            )
        )
    }

    override fun call(): Boolean {
        logger.info("EmployeeCallable {} is waiting in line", employee?.name)
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
        coffeeProcessor.callMakeCoffee(employee, coffee.name, coffee, payment, postActions, this)
        coffeeProcessor.runAllCalls(this)
        coffeeProcessor.waitForAllCalls(this)
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PreActionCallable::class.java)
    }
}
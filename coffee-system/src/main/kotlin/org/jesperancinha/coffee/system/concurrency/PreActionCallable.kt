package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.jesperancinha.coffee.system.objects.ActionDescriptor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.*
import java.util.function.Consumer

@Service
class PreActionCallable(
    @Autowired
    val machineProcessor: MachineProcessorImpl
) : ActionCallable(), QueueCallable {
    private var coffee: Coffee? = null
    private var payment: Payment? = null
    private var postActions: List<PostAction>? = null
    private var employee: Employee? = null
    override var name: String? = null

    fun init(
        employee: Employee,
        name: String,
        coffee: Coffee,
        payment: Payment,
        postActions: List<PostAction>
    ) {
        this.employee = employee
        this.name = name
        this.coffee = coffee
        this.payment = payment
        this.postActions = postActions
    }

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
        val coffeeProcessor = machineProcessor.coffeeProcessor
        machineProcessor.callMakeCoffee(
            requireNotNull(employee),
            requireNotNull(coffee?.name),
            requireNotNull(coffee),
            requireNotNull(payment),
            requireNotNull(postActions),
            this
        )
        coffeeProcessor.runAllCalls(this)
        coffeeProcessor.waitForAllCalls(this)
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PreActionCallable::class.java)
    }
}
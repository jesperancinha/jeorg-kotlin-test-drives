package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.GeneralProcessor
import org.jesperancinha.coffee.system.manager.PostProcessor
import org.slf4j.Logger
import java.util.concurrent.*

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
class PaymentCallable(
    private val chosenPayment: Payment,
    private val employee: Employee,
    val name: String,
    private val postActions: List<PostAction>,
    private val postProcessor: PostProcessor
) : QueueCallableAbstract(), QueueCallable {

    @Throws(Exception::class)
    override fun call(): Boolean {
        logger.info("PaymentCallable with {}", chosenPayment.name)
        chosenPayment.time?.let {
            time->
            TimeUnit.MILLISECONDS.sleep(time.toLong())
            postActions.let {
                postProcessor.callPostActions(GeneralProcessor.MAIN_QUEUE_POST, it, this)
            }
            postProcessor.runAllCalls(this)
            postProcessor.waitForAllCalls(this)
        }

        return true
    }

    companion object {
        val logger: Logger = org.slf4j.LoggerFactory.getLogger(PaymentCallable::class.java)
    }
}
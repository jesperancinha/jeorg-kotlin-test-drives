package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.TimeUnit

class CoffeeCallable(fillTime: FillTime, name: String?) : QueueCallableAbstract(),
    QueueCallable {
    private val fillTime: FillTime
    private val name: String?

    init {
        this.fillTime = fillTime
        this.name = name
    }

    override fun call(): Boolean {
        logger.info(
            MessageFormat.format( //
                "{0} - Starting task {1} to make coffee",  //
                fillTime.index,  //
                fillTime.description //
            )
        )
        try {
            TimeUnit.MILLISECONDS.sleep(fillTime.value.toLong())
        } catch (e: InterruptedException) {
            logger.error(e.message, e)
        }
        return true
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CoffeeCallable::class.java)
    }
}
package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import lombok.Getter
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.*

@Getter
@Service
@Slf4j
class CoffeeCallableImpl internal constructor(fillTime: FillTime, name: String?) : QueueCallableAbstract(),
    QueueCallable {
    private val fillTime: FillTime
    private val name: String?

    init {
        this.fillTime = fillTime
        this.name = name
    }

    override fun call(): Boolean {
        CoffeeCallableImpl.log.info(
            MessageFormat.format( //
                "{0} - Starting task {1} to make coffee",  //
                fillTime.getIndex(),  //
                fillTime.getDescription() //
            )
        )
        try {
            TimeUnit.MILLISECONDS.sleep(fillTime.getValue().toLong())
        } catch (e: InterruptedException) {
            CoffeeCallableImpl.log.error(e.message, e)
        }
        return true
    }
}
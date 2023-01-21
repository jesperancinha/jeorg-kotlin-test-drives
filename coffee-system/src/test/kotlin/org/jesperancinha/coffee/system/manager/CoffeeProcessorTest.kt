package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.PreActionCallable
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@SpringBootTest
class CoffeeProcessorTest @Autowired constructor(
    val coffeeProcessor: CoffeeProcessor,
) {
    @Test
    fun `should run coffee processor with imported data`() {
        val preActionCallable = PreActionCallable(
            coffeeProcessor,
            employee = employee,
            name = "Callable",
            coffee = coffee,
            payment = payment,
            postActions = postActions
        )
        coffeeProcessor.callMakeCoffee(
            name = "King processor",
            employee = employee,
            coffee = coffee,
            parentCallable = preActionCallable,
            payment = payment,
            postActions = postActions
        )

    }
}
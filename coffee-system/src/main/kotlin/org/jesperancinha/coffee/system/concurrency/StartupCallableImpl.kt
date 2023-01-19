package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import java.util.concurrent.Callable

/**
 * Created by joao on 8-5-16.
 */
class StartupCallableImpl : QueueCallableAbstract(), QueueCallable {
    @Throws(Exception::class)
    override fun call() = true
}
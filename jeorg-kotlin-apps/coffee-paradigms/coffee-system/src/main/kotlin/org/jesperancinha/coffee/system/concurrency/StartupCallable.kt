package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable

/**
 * Created by joao on 8-5-16.
 */
class StartupCallable : QueueCallableAbstract(), QueueCallable {
    @Throws(Exception::class)
    override fun call() = true
}
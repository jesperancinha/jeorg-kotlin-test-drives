package org.jesperancinha.coffee.system.objects

import java.util.function.Consumer

/**
 * Created by joao on 8-5-16.
 */
interface ConsumerWithException<T> : Consumer<T> {
    @Throws(Exception::class)
    fun acceptWithException(t: T)
}
package org.jesperancinha.coffee.system.concurrency

import lombok.Getter
import org.jesperancinha.coffee.system.objects.ActionDescriptor
import org.slf4j.LoggerFactory

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
abstract class ActionCallable internal constructor(protected var name: String) : QueueCallableAbstract() {
    protected var actionDescriptorList: MutableList<ActionDescriptor> = ArrayList()

    companion object {
        private val logger = LoggerFactory.getLogger(ActionCallable::class.java)
    }
}
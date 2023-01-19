package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.objects.ActionDescriptor

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
abstract class ActionCallable() : QueueCallableAbstract() {
    abstract var name: String?
    protected var actionDescriptorList: MutableList<ActionDescriptor> = mutableListOf()
}
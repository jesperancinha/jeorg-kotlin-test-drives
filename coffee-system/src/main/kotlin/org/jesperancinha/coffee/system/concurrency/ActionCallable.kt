package org.jesperancinha.coffee.system.concurrency

import lombok.Getter
import org.jesperancinha.coffee.system.objects.ActionDescriptor

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
abstract class ActionCallable internal constructor(var name: String) : QueueCallableAbstract() {
    protected var actionDescriptorList: MutableList<ActionDescriptor> = mutableListOf()
}
package org.jesperancinha.coffee.system.concurrency

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.jesperancinha.coffee.system.objects.ActionDescriptor
import lombok.Getter
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.*
import java.util.function.Consumer

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
@Getter
@Slf4j
class PostActionCallableImpl(name: String?) : ActionCallable(name), QueueCallable {
    @Autowired
    private val machineProcessor: MachineProcessorImpl? = null
    fun addPostAction(postAction: PostAction) {
        actionDescriptorList
            .add(
                ActionDescriptor.builder().description(postAction.getDescription()).time(postAction.getTime())
                    .build()
            )
    }

    override fun call(): Boolean {
        actionDescriptorList.forEach(
            Consumer { actionDescriptor: ActionDescriptor ->
                PostActionCallableImpl.log.info(MessageFormat.format("Ending with {0}", actionDescriptor.description))
                try {
                    TimeUnit.MILLISECONDS.sleep(actionDescriptor.time.toLong())
                } catch (e: InterruptedException) {
                    PostActionCallableImpl.log.error(e.message, e)
                }
            }
        )
        return true
    }
}
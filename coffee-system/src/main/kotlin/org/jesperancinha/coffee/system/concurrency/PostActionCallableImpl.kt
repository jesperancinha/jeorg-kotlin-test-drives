package org.jesperancinha.coffee.system.concurrency

import lombok.Getter
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.manager.MachineProcessorImpl
import org.jesperancinha.coffee.system.objects.ActionDescriptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
@Getter
@Slf4j
class PostActionCallableImpl(name: String) : ActionCallable(name), QueueCallable {
    @Autowired
    private val machineProcessor: MachineProcessorImpl? = null
    fun addPostAction(postAction: PostAction) {
        actionDescriptorList
            .add(
                ActionDescriptor(
                    description = postAction.description,
                    time = postAction.time
                )
            )
    }

    override fun call(): Boolean {
        actionDescriptorList.forEach(
            Consumer { actionDescriptor: ActionDescriptor ->
                logger.info(MessageFormat.format("Ending with {0}", actionDescriptor.description))
                try {
                    TimeUnit.MILLISECONDS.sleep(actionDescriptor.time.toLong())
                } catch (e: InterruptedException) {
                    logger.error(e.message, e)
                }
            }
        )
        return true
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(PostActionCallableImpl::class.java)
    }
}
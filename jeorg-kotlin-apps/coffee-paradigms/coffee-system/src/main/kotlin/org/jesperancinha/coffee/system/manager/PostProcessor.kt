package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.ActionCallable
import org.jesperancinha.coffee.system.concurrency.PostActionCallable
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.Queue
import org.jesperancinha.coffee.system.queues.QueuePostActivityImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable

@Service
class PostProcessor(
    @Autowired
    private val queuePostActivity: QueuePostActivityImpl,
) : ProcessorAbstract() {

    fun callPostActions(
        name: String,
        postActions: List<PostAction>,
        parentCallable: QueueCallable
    ) {
        val postActionCallable = PostActionCallable(name)
        parentCallable.allCallables.add(postActionCallable)
        postActions.forEach { postAction -> postActionCallable.addPostAction(postAction) }
    }

    override val executorServiceQueue: Queue = queuePostActivity

    override fun getExecutorName(callable: Callable<Boolean>) = requireNotNull((callable as ActionCallable).name)

    fun addQueueSize(queueSize: Int, name: String) {
        queuePostActivity.setQueueSize(queueSize, name)
    }

    fun initExecutors() {
        queuePostActivity.initExecutors()
    }

    fun stopExecutors() {
        queuePostActivity.stopExecutors()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PostProcessor::class.java)
    }
}
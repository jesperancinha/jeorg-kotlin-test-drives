package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.ActionCallable
import org.jesperancinha.coffee.system.concurrency.PostActionCallableImpl
import com.jesperancinha.coffee.system.queues.*
import lombok.Getter
import lombok.experimental.Accessors
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.Queue
import org.jesperancinha.coffee.system.queues.QueuePostActivityImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Callable
import java.util.function.Consumer

@Accessors(chain = true)
@Getter
@Service
abstract class PostProcessorImpl : ProcessorAbstract() {
    @Autowired
    private val queuePostActivity: QueuePostActivityImpl? = null
    fun callPostActions(
        employee: Employee?,
        name: String?,
        postActions: List<PostAction?>,
        parentCallable: QueueCallable
    ) {
        val postActionCallable = PostActionCallableImpl(name)
        parentCallable.allCallables.add(postActionCallable)
        postActions.forEach(Consumer<PostAction> { postAction: PostAction -> postActionCallable.addPostAction(postAction) })
    }

    override val executorServiceQueue: Queue?
        get() = queuePostActivity

    override fun getExecutorName(callable: Callable<Boolean?>): String {
        return (callable as ActionCallable).name
    }

    fun addQueueSize(queueSize: Int, name: String?) {
        queuePostActivity!!.setQueueSize(queueSize, name!!)
    }

    fun initExecutors() {
        queuePostActivity!!.initExecutors()
    }

    fun stopExectutors() {
        queuePostActivity!!.stopExecutors()
    }

    abstract override fun waitForAllCalls(queueCallable: QueueCallable)
    abstract override fun runAllCalls(queueCallable: QueueCallable)

    companion object {
        private val logger = LoggerFactory.getLogger(PostProcessorImpl::class.java)
    }
}
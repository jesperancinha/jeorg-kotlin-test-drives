package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.PostActionCallableImpl;
import com.steelzack.coffee.system.concurrency.PreActionCallableImpl;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueuePostActivityImpl;
import com.steelzack.coffee.system.queues.QueuePreActivityImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static com.steelzack.coffee.system.concurrency.EmployeeCallableImpl.SCHEDULED_TASK_FAILED_TO_EXECUTE;

@Accessors(chain = true)
@Getter
@Service
public class EmployeeProcessorImpl extends ProcessorImpl implements EmployeeProcessor {

    private static final Logger logger = Logger.getLogger(EmployeeProcessorImpl.class);

    private Actions actions;
    private int queueSize;

    @Autowired
    private QueuePreActivityImpl queuePreActivity;

    @Autowired
    private QueuePostActivityImpl queuePostActivity;

    @Override
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void callPreActions(final String name) {
        final List<Actions.PreAction> preActions = this.actions.getPreAction();
        final ExecutorService executor = queuePreActivity.getExecutor(name);
        preActions.stream().forEach( //
                preAction -> { //
                    try {
                        if (!executor.submit(new PreActionCallableImpl(preAction)).get()) {
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                } //
        ); //
    }

    @Override
    public void callPostActions(final String name) {
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        final ExecutorService executor = queuePostActivity.getExecutor(name);
        postActions.stream().forEach( //
                postAction -> { //
                    try {
                        if (!executor.submit(new PostActionCallableImpl(postAction)).get()) {
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                } //
        ); //
    }

    @Override
    public void setPostQueueSize(int queueSize, String name) {
        queuePostActivity.setQueueSize(queueSize, name);
    }

    @Override
    QueueAbstract getExecutorService() {
        return queuePreActivity;
    }

    @Override
    public void addQueueSize(int queueSize, String name) {
        queuePreActivity.setQueueSize(queueSize, name);
    }

    @Override
    public void initExecutors() {
        queuePreActivity.initExecutors();
        queuePostActivity.initExecutors();
    }
}

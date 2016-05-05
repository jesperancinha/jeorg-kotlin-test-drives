package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.PostActionCallableImpl;
import com.steelzack.coffee.system.concurrency.PreActionCallableImpl;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.steelzack.coffee.system.utils.ExecutorServiceHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.steelzack.coffee.system.concurrency.EmployeeCallableImpl.SCHEDULED_TASK_FAILED_TO_EXECUTE;

@Accessors(chain = true)
@Builder
@Getter
@Service
public class EmployeeProcessorImpl extends ProcessorImpl implements EmployeeProcessor {

    private static final Logger logger = Logger.getLogger(EmployeeProcessorImpl.class);

    private Actions actions;
    private int queueSize;
    private ExecutorService postManagedExecutorService;

    @Override
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void callPreActions() {
        final List<Actions.PreAction> preActions = this.actions.getPreAction();
        preActions.stream().forEach( //
                preAction -> { //
                    try {
                        if (!managedExecutorService.submit(new PreActionCallableImpl(preAction)).get()) {
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                } //
        ); //
    }

    @Override
    public void callPostActions() {
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        postActions.stream().forEach( //
                postAction -> { //
                    try {
                        if (!postManagedExecutorService.submit(new PostActionCallableImpl(postAction)).get()) {
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(), e);
                    }
                } //
        ); //
    }

    @Override
    public void setPostQueueSize(int queueSize) {
        ExecutorServiceHelper.shutDownExecutorService(postManagedExecutorService);
        postManagedExecutorService = Executors.newFixedThreadPool(queueSize);
    }

}

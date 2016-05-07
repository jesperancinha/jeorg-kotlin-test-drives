package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.ActionCallable;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@Accessors(chain = true)
@Getter
@Service
public class EmployeeProcessorImpl extends ProcessorAbstract implements EmployeeProcessor {

    private static final Logger logger = Logger.getLogger(EmployeeProcessorImpl.class);

    private Actions actions;

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
                    allResults.add(executor.submit(new PreActionCallableImpl(preAction, name)));
                } //
        ); //
    }

    @Override
    public void callPostActions(final String name) {
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        final ExecutorService executor = queuePostActivity.getExecutor(name);
        postActions.stream().forEach( //
                postAction -> { //
                    allResults.add(executor.submit(new PostActionCallableImpl(postAction, name)));
                } //
        ); //
    }

    @Override
    public void addPostQueueSize(int queueSize, String name) {
        queuePostActivity.setQueueSize(queueSize, name);
    }

    @Override
    public QueueAbstract getExecutorService() {
        return queuePreActivity;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((ActionCallable)callable).getName();
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

    @Override
    public void stopExectutors() {
        queuePreActivity.stopExecutors();
        queuePostActivity.stopExecutors();
    }
}

package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.api.concurrency.PostActionCallable;
import com.jesperancinha.coffee.api.manager.MachineProcessor;
import com.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.jesperancinha.coffee.system.objects.ActionDescriptor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
@Getter
public class PostActionCallableImpl extends ActionCallable implements PostActionCallable {

    private static final Logger logger = LoggerFactory.getLogger(PreActionCallableImpl.class);
    @Autowired
    private MachineProcessor machineProcessor;

    public PostActionCallableImpl(String name) {
        super(name);

    }

    @Override
    public void addPostAction(PostAction postAction) {
        this.actionDescriptorList
                .add(ActionDescriptor.builder().description(postAction.getDescription()).time(postAction.getTime())
                        .build());
    }

    public Boolean call() {
        this.actionDescriptorList.forEach(
                actionDescriptor -> {
                    logger.info(MessageFormat.format("Ending with {0}", actionDescriptor.getDescription()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(actionDescriptor.getTime());
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
        );
        return true;
    }

}

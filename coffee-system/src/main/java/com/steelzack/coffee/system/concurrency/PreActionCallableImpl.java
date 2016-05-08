package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.steelzack.coffee.system.manager.MachineProcessor;
import com.steelzack.coffee.system.objects.ActionDescriptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
public class PreActionCallableImpl extends ActionCallable implements PreActionCallable{
    private final static Logger logger = Logger.getLogger(PreActionCallableImpl.class);

    @Autowired
    private MachineProcessor machineProcessor;


    public PreActionCallableImpl(String name) {
        super(name);
    }

    @Override
    public void addPreAction(PreAction preAction) {
        this.actionDescriptorList.add(ActionDescriptor.builder().description(preAction.getDescription()).time(preAction.getTime()).build());
    }

    @Override
    public Boolean call() throws InterruptedException {
        this.actionDescriptorList.stream().forEach(
                actionDescriptor -> {
                    logger.info(MessageFormat.format("Starting with {0}", actionDescriptor.getDescription()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(actionDescriptor.getTime());
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                }
        );
        return true;
    }
}

package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.steelzack.coffee.system.manager.MachineProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
public class PreActionCallableImpl extends ActionCallable {
    private final static Logger logger = Logger.getLogger(PreActionCallableImpl.class);

    @Autowired
    private MachineProcessor machineProcessor;


    public PreActionCallableImpl(PreAction preAction, String name) {
        super(preAction.getDescription(), preAction.getTime(), name);
    }

    @Override
    public Boolean call() throws InterruptedException {
        logger.info(MessageFormat.format("Starting with {0}", description));
        TimeUnit.MILLISECONDS.sleep(time);
        return true;
    }
}

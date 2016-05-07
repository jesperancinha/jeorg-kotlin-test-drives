package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.manager.MachineProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public class PostActionCallableImpl extends ActionCallable {

    @Autowired
    private MachineProcessor machineProcessor;

    final static Logger logger = Logger.getLogger(PreActionCallableImpl.class);

    public PostActionCallableImpl(Employees.Employee.Actions.PostAction postAction) {
        super(postAction.getDescription(), postAction.getTime());
    }

    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("Ending with {0}", description));
        TimeUnit.MILLISECONDS.sleep(time);
        return true;
    }
}

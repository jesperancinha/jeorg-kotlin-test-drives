package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.manager.MachineProcessor;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Service
@Getter
public class PostActionCallableImpl extends ActionCallable {

    @Autowired
    private MachineProcessor machineProcessor;

    final static Logger logger = Logger.getLogger(PreActionCallableImpl.class);

    public PostActionCallableImpl(Employees.Employee.Actions.PostAction postAction, String name) {
        super(postAction.getDescription(), postAction.getTime(), name);

    }

    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("Ending with {0}", description));
        TimeUnit.MILLISECONDS.sleep(time);
        return true;
    }
}

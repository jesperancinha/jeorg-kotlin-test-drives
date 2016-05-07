package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
public class PostActionCallableImpl extends ActionCallable {
    final static Logger logger = Logger.getLogger(PreActionCallableImpl.class);

    public PostActionCallableImpl(Employees.Employee.Actions.PostAction postAction) {
        super(postAction.getDescription(), postAction.getTime());
    }

    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("Ending with {0}", description));
        TimeUnit.MILLISECONDS.sleep(time.longValue());
        return true;
    }
}

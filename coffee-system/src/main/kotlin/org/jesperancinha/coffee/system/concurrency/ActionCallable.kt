package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.system.objects.ActionDescriptor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
public abstract class ActionCallable extends QueueCallableAbstract {

    private static final Logger logger = LoggerFactory.getLogger(ActionCallable.class);

    protected String name;

    protected List<ActionDescriptor> actionDescriptorList = new ArrayList<>();

    ActionCallable(String name) {
        this.name = name;
    }

}

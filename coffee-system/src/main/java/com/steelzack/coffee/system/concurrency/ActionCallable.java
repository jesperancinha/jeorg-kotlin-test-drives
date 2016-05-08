package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.objects.ActionDescriptor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
public abstract class ActionCallable {

    private String name;

     List<ActionDescriptor> actionDescriptorList = new ArrayList<>();

    ActionCallable(String name) {
        this.name = name;
    }

}

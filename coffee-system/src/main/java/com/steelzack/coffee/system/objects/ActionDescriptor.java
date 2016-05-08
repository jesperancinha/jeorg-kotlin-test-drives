package com.steelzack.coffee.system.objects;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by joao on 8-5-16.
 */
@Builder
@Getter
public class ActionDescriptor {

    private final String description;
    private final Integer time;

}

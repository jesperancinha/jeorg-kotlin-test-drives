package com.steelzack.coffee.system.concurrency;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by joao on 8-5-16.
 */
public interface StartupCallable extends QueueCallable{
    List<Callable<Boolean>> getAllCallables();
}

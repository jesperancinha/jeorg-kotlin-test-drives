package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.sun.javafx.binding.StringFormatter;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joao on 29-4-16.
 */
@Getter
public class CoffeeCallableImpl implements Coffe, Callable<Boolean> {

    final Logger logger = Logger.getLogger(CoffeeCallableImpl.class);

    private FillTime fillTime;

    public CoffeeCallableImpl(FillTime fillTime)
    {
        this.fillTime = fillTime;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(StringFormatter.format( //
                "{0} - Starting tast {1} to make coffee", //
                fillTime.getIndex(), //
                fillTime.getDescription() //
        ));
        TimeUnit.MILLISECONDS.wait(fillTime.getValue());
        return true;
    }

}

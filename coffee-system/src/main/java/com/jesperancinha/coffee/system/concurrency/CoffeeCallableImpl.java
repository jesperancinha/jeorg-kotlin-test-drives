package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.api.concurrency.CoffeCallable;
import com.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by joao on 29-4-16.
 */
@Getter
@Service
public class CoffeeCallableImpl extends QueueCallableAbstract implements CoffeCallable {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeCallableImpl.class);
    private FillTime fillTime;
    private String name;

    CoffeeCallableImpl(FillTime fillTime, String name) {
        this.fillTime = fillTime;
        this.name = name;
    }

    @Override
    public Boolean call() {
        logger.info(MessageFormat.format( //
                "{0} - Starting task {1} to make coffee", //
                fillTime.getIndex(), //
                fillTime.getDescription() //
        ));
        try {
            TimeUnit.MILLISECONDS.sleep(fillTime.getValue());
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        return true;
    }

}

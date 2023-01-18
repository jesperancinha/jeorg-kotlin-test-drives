package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.system.api.concurrency.QueueCallable;
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;


@Getter
@Service
@Slf4j
public class CoffeeCallableImpl extends QueueCallableAbstract implements QueueCallable {

    private FillTime fillTime;
    private String name;

    CoffeeCallableImpl(FillTime fillTime, String name) {
        this.fillTime = fillTime;
        this.name = name;
    }

    @Override
    public Boolean call() {
        CoffeeCallableImpl.log.info(MessageFormat.format( //
                "{0} - Starting task {1} to make coffee", //
                fillTime.getIndex(), //
                fillTime.getDescription() //
        ));
        try {
            TimeUnit.MILLISECONDS.sleep(fillTime.getValue());
        } catch (InterruptedException e) {
            CoffeeCallableImpl.log.error(e.getMessage(), e);
        }
        return true;
    }

}

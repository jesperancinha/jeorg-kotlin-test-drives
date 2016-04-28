package com.steelzack.coffee.system.objecs;

import com.steelzack.coffee.system.enums.Coffee;
import com.steelzack.coffee.system.enums.Payment;

/**
 * Created by joao on 28-4-16.
 */
public interface Programmer {
    void addOffset(int offset);

    void addTimeStamp(int timeStamp);

    boolean isDonePick();

    boolean isDonePay();

    boolean isDoneGet();

    int getCompleteTime();

    Coffee getCoffee();

    Payment getPayment();
}

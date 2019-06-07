package com.jesperancinha.coffee.system.objects;

import java.util.function.Consumer;

/**
 * Created by joao on 8-5-16.
 */
public interface  ConsumerWithException<T>  extends Consumer<T>{

    void acceptWithException(T t) throws Exception;

}

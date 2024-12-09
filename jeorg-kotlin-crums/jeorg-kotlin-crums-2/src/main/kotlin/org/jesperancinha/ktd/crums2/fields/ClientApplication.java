package org.jesperancinha.ktd.crums2.fields;

public class ClientApplication {

    public void runClient() {
        var example = new Example();
        var someValue = example.someValue;
        System.out.println(someValue);
        var somveValue2 = JvmFields.CONSTANT;
        System.out.println(somveValue2);
    }
}

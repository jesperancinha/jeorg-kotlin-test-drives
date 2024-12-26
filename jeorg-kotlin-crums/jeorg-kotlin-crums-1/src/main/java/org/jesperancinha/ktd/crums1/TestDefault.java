package org.jesperancinha.ktd.crums1;

public class TestDefault {
    public void testDefault() {
        var greet = new Greetable(){

            @Override
            public void greet() {
                System.out.println("Hello World");
            }
        };
        greet.greet();

    }
}

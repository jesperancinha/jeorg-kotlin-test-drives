package org.jesperancinha.ktd.overloads;

public class GreetJava {
    public static void main(String[] args) {
        Greet greet = new Greet();
        String result1 = greet.greet("John");
        String result2 = greet.greet("John", "Hi");

        System.out.println(result1);
        System.out.println(result2);

        greet.greet("Wow");
    }
}

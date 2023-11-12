package org.jesperancinha.ktd.delegates;


public record  Client(String name, String address) implements Person{
    @Override
    public String name() {
        return name;
    }

    @Override
    public String address() {
        return address;
    }
}

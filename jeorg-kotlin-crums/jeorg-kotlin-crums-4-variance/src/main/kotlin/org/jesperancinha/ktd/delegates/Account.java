package org.jesperancinha.ktd.delegates;

import java.time.LocalDateTime;

public record Account(
        Person person,
        Card card,
        String number

) implements Card, Person {
    @Override
    public LocalDateTime expiryDate() {
        return card.expiryDate();
    }

    @Override
    public Long cardNumber() {
        return card.cardNumber();
    }

    @Override
    public String name() {
        return person.name();
    }

    @Override
    public String address() {
        return person.address();
    }

    public static void main(String[] args) {
        var account = new Account(
                new Client(
                        "João",
                        "World"
                ),
                new DebitCard(
                        1111111111111111L,
                        LocalDateTime.now()
                ),
                "AAABBB12312313-3243242-23432"
        );
        System.out.println(account.number);
        System.out.println(account.name());
        System.out.println(account.address());
        System.out.println(account.cardNumber());
        System.out.println(account.expiryDate());
    }
}

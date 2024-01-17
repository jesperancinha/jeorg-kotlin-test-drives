package org.jesperancinha.ktd.java;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

    public class ValidationUsersEmail {
        public static void main(String[] args) {
            try(var factory = buildDefaultValidatorFactory()) {
                var validator = factory.getValidator();
                var validate1 = validator.validate(new User("bad"));
                System.out.println(validate1);
                var validate2 = validator.validate(new User("user@someemail.com"));
                System.out.println(validate2);
            }
        }
    }

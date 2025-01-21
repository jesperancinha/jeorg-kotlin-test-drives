package org.jesperancinha.ktd.java;

import jakarta.validation.constraints.Email;

    public class User {

        @Email(message = "Please insert a valid email")
        private String email;

        public User(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

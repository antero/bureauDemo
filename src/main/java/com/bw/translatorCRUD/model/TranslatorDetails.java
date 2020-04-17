package com.bw.translatorCRUD.model;

import javax.validation.constraints.Email;

public class TranslatorDetails {
    private String name;
    @Email(message = "Email must be a valid email")
    private String email;

    public TranslatorDetails() {}

    public TranslatorDetails(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().isEmpty() ? null : name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().isEmpty() ? null : email;
    }
}

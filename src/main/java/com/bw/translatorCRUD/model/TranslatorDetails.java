package com.bw.translatorCRUD.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Email;

@JsonPropertyOrder({ "id", "name", "email" })
public class TranslatorDetails {
    private long id;
    private String name;
    @Email(message = "Email must be a valid email")
    private String email;

    public TranslatorDetails() {}

    public TranslatorDetails(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public TranslatorDetails(long id, String name, String email) {
        this(name, email);
        this.id = id;
    }

    public static TranslatorDetails of(Translator translator) {
        return new TranslatorDetails(translator.getId(), translator.getName(), translator.getEmail());
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

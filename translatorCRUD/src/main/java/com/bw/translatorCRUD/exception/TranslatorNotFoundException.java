package com.bw.translatorCRUD.exception;

public class TranslatorNotFoundException extends RuntimeException {
    public TranslatorNotFoundException(Long id) {
        super("Could not find user " + id);
    }
    public TranslatorNotFoundException(String email) {
        super("Could not find user with email " + email);
    }
}

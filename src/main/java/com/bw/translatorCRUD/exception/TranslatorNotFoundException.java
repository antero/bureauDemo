package com.bw.translatorCRUD.exception;

public class TranslatorNotFoundException extends RuntimeException {
    public TranslatorNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}

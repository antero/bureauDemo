package com.bw.translatorCRUD.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super(email + " is already taken");
    }
}

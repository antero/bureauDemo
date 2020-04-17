package com.bw.translatorCRUD.exception;

public class TranslationSkillNotFoundException extends RuntimeException {
    public TranslationSkillNotFoundException(String source, String target) {
        super("Could not translation skill from " + source + " to " + target);
    }
}

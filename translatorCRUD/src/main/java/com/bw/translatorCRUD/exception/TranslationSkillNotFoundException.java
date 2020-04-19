package com.bw.translatorCRUD.exception;

import com.bw.translatorCRUD.model.Translator;

public class TranslationSkillNotFoundException extends RuntimeException {
    public TranslationSkillNotFoundException(Long id) {super("Could not translation skill from " + id);}
    public TranslationSkillNotFoundException(String source, String target) {
        super("Could not translation skill from " + source + " to " + target);
    }
}

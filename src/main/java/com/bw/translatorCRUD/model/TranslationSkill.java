package com.bw.translatorCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class TranslationSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    private String sourceLanguage;
    @NotEmpty
    private String targetLanguage;

    public TranslationSkill() {}

    public TranslationSkill(String sourceLanguage, String targetLanguage) {
        this.sourceLanguage = sourceLanguage.toLowerCase();
        this.targetLanguage = targetLanguage.toLowerCase();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage.toLowerCase();
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage.toLowerCase();
    }
}

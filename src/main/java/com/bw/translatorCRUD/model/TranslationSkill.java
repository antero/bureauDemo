package com.bw.translatorCRUD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class TranslationSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String sourceLanguage;
    @NotEmpty
    private String targetLanguage;

    @ManyToMany(mappedBy = "translationSkills")
    @JsonIgnoreProperties("translationSkills")
    private Set<Translator> translators;

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

    public Set<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(Set<Translator> translators) {
        this.translators = translators;
    }
}

package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.TranslationSkill;

import java.util.List;
import java.util.Optional;

public interface TranslationSkillService {
    List<TranslationSkill> findAll();

    TranslationSkill findById(Long id);

    TranslationSkill create(String source, String target);

    Optional<TranslationSkill> findByLanguagePair(String source, String target);

    TranslationSkill getOrCreateLanguagePair(String source, String target);
}

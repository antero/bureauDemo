package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.exception.TranslationSkillNotFoundException;
import com.bw.translatorCRUD.exception.TranslatorNotFoundException;
import com.bw.translatorCRUD.model.TranslationSkill;
import com.bw.translatorCRUD.repository.TranslationSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TranslationSkillServiceImpl implements TranslationSkillService {
    @Autowired
    private TranslationSkillRepository translationSkillRepository;


    @Override
    public List<TranslationSkill> findAll() {
        return translationSkillRepository.findAll();
    }

    @Override
    public TranslationSkill findById(Long id) {
        return translationSkillRepository.findById(id)
                                         .orElseThrow(() -> new TranslationSkillNotFoundException(id));
    }

    @Override
    public TranslationSkill create(String source, String target) {
        TranslationSkill skill = new TranslationSkill(source, target);
        return translationSkillRepository.save(skill);
    }

    @Override
    public Optional<TranslationSkill> findByLanguagePair(String source, String target) {
        return findAll().stream()
                        .filter(ts -> ts.getSourceLanguage().equals(source.toLowerCase())
                                   && ts.getTargetLanguage().equals(target.toLowerCase()))
                        .findFirst();
    }

    @Override
    public TranslationSkill getOrCreateLanguagePair(String source, String target) {
        return findByLanguagePair(source, target)
                          .orElse(create(source, target));
    }
}

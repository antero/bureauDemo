package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.exception.EmailAlreadyExistsException;
import com.bw.translatorCRUD.exception.TranslatorNotFoundException;
import com.bw.translatorCRUD.model.TranslationSkill;
import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.model.TranslatorDetails;
import com.bw.translatorCRUD.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TranslatorServiceImpl implements TranslatorService {
    @Autowired
    private TranslatorRepository translatorRepository;

    @Autowired
    private TranslationSkillService translationSkillService;

    @Override
    public List<Translator> findAll() {
        return translatorRepository.findAll();
    }

    @Override
    public Translator create(Translator translator) {
        if (translatorRepository.findByEmail(translator.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(translator.getEmail());
        }

        List<TranslationSkill> skills = new ArrayList<>(
                Optional.ofNullable(translator.getTranslationSkills()).orElse(new HashSet<>())
        );
        Optional.ofNullable(translator.getTranslationSkills()).ifPresent(Set::clear);
        addTranslationSkillsToTranslatorObject(translator, skills);

        return translatorRepository.save(translator);
    }

    @Override
    public Translator findById(Long id) {
        return translatorRepository.findById(id)
                                   .orElseThrow(() -> new TranslatorNotFoundException(id));
    }

    @Override
    public Translator update(Long id, TranslatorDetails translatorDetails) {
        Translator translator = findById(id);

        String newEmail = Optional.ofNullable(translatorDetails.getEmail()).orElse(translator.getEmail());
        if (!newEmail.equals(translator.getEmail())
                && translatorRepository.findByEmail(newEmail).isPresent()) {
            throw new EmailAlreadyExistsException(newEmail);
        }

        translator.setName(Optional.ofNullable(translatorDetails.getName()).orElse(translator.getName()));
        translator.setEmail(newEmail);

        List<TranslationSkill> skills = Optional.ofNullable(translatorDetails.getTranslationSkills()).orElse(new ArrayList<>());
        addTranslationSkillsToTranslatorObject(translator, skills);

        return translatorRepository.save(translator);
    }

    @Override
    public void deleteById(Long id) {
        try {
            translatorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TranslatorNotFoundException(id);
        }
    }

    @Override
    public Translator addTranslationSkills(Long id, List<TranslationSkill> translationSkills) {
        Translator translator = findById(id);
        addTranslationSkillsToTranslatorObject(translator, translationSkills);

        return translatorRepository.save(translator);
    }

    private void addTranslationSkillsToTranslatorObject(Translator translator, List<TranslationSkill> translationSkills) {
        LinkedHashSet<Pair<String, String>> skillsHashSet = new LinkedHashSet<>(
                translationSkills.stream().map(s -> Pair.of(s.getSourceLanguage(), s.getTargetLanguage()))
                        .collect(Collectors.toList())
        );

        skillsHashSet.stream()
                .map(p -> translationSkillService.getOrCreateLanguagePair(p.getFirst(), p.getSecond()))
                .forEach(s -> translator.getTranslationSkills().add(s));
    }
}

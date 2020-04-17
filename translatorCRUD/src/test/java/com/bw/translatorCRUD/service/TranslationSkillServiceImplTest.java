package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.TranslationSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TranslationSkillServiceImplTest {
    @Autowired
    private TranslationSkillService translationSkillService;

    @Test
    void findByNonExistentLanguagePair() {
        assertTrue(translationSkillService.findByLanguagePair("no_lang1", "no_lang2").isEmpty());
    }

    @Test
    void findByExistentLanguagePair() {
        translationSkillService.create("pt_Br", "en_uS");
        assertTrue(translationSkillService.findByLanguagePair("pt_br", "en_us").isPresent());
    }
}
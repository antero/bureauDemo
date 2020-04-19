package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.TranslationSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

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
        translationSkillService.create("pt_br", "en_us");
        assertTrue(translationSkillService.findByLanguagePair("pt_br", "en_us").isPresent());
    }

    @Test
    void getOrCreateLanguagePairDoesNotCreateRepeated() {
        TranslationSkill ts1 =  translationSkillService.create("no_lang1", "no_lang2");
        TranslationSkill ts2 =  translationSkillService.getOrCreateLanguagePair("no_lang1", "no_lang2");

        assertEquals(ts1.getId(), ts2.getId());
    }
}
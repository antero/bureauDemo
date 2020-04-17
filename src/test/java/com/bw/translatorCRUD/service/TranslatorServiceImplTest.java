package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.repository.TranslatorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TranslatorServiceImplTest {
    @Autowired
    private TranslatorRepository translatorRepository;

    @Autowired
    private TranslatorService translatorService;

    @Test
    void findAll() {
        Translator t1 = new Translator();
        t1.setName("Fulano Silva");
        t1.setEmail("fulano@gmail.com");
        Translator t2 = new Translator();
        t2.setName("Sicrano Jose");
        t2.setEmail("sicrano@gmail.com");

        translatorRepository.saveAll(Arrays.asList(t1, t2));
        assertEquals(2, translatorService.findAll().size());
    }
}
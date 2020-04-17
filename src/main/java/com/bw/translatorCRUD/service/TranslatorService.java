package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.Translator;

import java.util.List;
import java.util.Map;

public interface TranslatorService {
    List<Translator> findAll();

    Translator create(Translator translator);

    Translator findById(Long id);

    Translator update(Long id, Map<String, Object> payload);

    void deleteById(Long id);
}

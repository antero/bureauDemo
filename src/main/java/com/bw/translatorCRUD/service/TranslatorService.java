package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.model.TranslatorDetails;

import java.util.List;

public interface TranslatorService {
    List<Translator> findAll();

    Translator create(Translator translator);

    Translator findById(Long id);

    Translator update(Long id, TranslatorDetails translatorDetails);

    void deleteById(Long id);
}

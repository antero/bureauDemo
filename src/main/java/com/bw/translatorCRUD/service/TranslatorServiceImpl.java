package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorServiceImpl implements TranslatorService {
    @Autowired
    private TranslatorRepository translatorRepository;

    @Override
    public List<Translator> findAll() {
        return translatorRepository.findAll();
    }
}

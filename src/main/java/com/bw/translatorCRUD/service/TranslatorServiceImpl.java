package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.exception.TranslatorNotFoundException;
import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.model.TranslatorDetails;
import com.bw.translatorCRUD.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TranslatorServiceImpl implements TranslatorService {
    @Autowired
    private TranslatorRepository translatorRepository;

    @Override
    public List<Translator> findAll() {
        return translatorRepository.findAll();
    }

    @Override
    public Translator create(Translator translator) {
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

        translator.setName(Optional.ofNullable(translatorDetails.getName()).orElse(translator.getName()));
        translator.setEmail(Optional.ofNullable(translatorDetails.getEmail()).orElse(translator.getEmail()));

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
}

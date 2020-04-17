package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.exception.TranslatorNotFoundException;
import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Translator update(Long id, Map<String, Object> payload) {
        Translator translator = findById(id);

        translator.setName((String) payload.getOrDefault("name", translator.getName()));
        translator.setEmail((String) payload.getOrDefault("email", translator.getEmail()));

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

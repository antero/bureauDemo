package com.bw.translatorCRUD.repository;

import com.bw.translatorCRUD.model.Translator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TranslatorRepository extends JpaRepository<Translator, Long> {
    Optional<Translator> findByEmail(String email);
}

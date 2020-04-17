package com.bw.translatorCRUD.controller;

import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.model.TranslatorDetails;
import com.bw.translatorCRUD.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("translators")
public class TranslatorController {
    @Autowired
    private TranslatorService translatorService;

    @GetMapping
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(translatorService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Translator translator) {
        return ResponseEntity.ok(translatorService.create(translator));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieve(@PathVariable Long id) {
        Translator translator = translatorService.findById(id);
        return ResponseEntity.ok(translator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody TranslatorDetails translatorDetails) {
        return ResponseEntity.ok(translatorService.update(id, translatorDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        translatorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

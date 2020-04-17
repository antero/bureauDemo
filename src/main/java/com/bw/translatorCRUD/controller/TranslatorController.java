package com.bw.translatorCRUD.controller;

import com.bw.translatorCRUD.exception.TranslatorNotFoundException;
import com.bw.translatorCRUD.model.ErrorResponse;
import com.bw.translatorCRUD.model.Translator;
import com.bw.translatorCRUD.service.TranslatorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Object> create(@RequestBody Translator translator) {
        return ResponseEntity.ok(translatorService.create(translator));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieve(@PathVariable Long id) {
        try {
            Translator translator = translatorService.findById(id);
            return ResponseEntity.ok(translator);
        } catch (TranslatorNotFoundException e) {
            ErrorResponse error = new ErrorResponse(e.getMessage());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(translatorService.update(id, payload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        translatorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

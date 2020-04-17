package com.bw.translatorCRUD.controller;

import com.bw.translatorCRUD.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class TranslatorController {
    @Autowired
    private TranslatorService translatorService;

    @GetMapping
    public ResponseEntity<Object> list() {
        return new ResponseEntity<>(translatorService.findAll(), HttpStatus.OK);
    }

}

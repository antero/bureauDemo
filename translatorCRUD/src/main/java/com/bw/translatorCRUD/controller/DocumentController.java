package com.bw.translatorCRUD.controller;

import com.bw.translatorCRUD.model.Document;
import com.bw.translatorCRUD.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String query) {
        List<Document> documents = documentService.fullTextSearch(query);
        return ResponseEntity.ok(documents);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(documentService.findAll());
    }
}

package com.bw.documentSearcher.controller;

import com.bw.documentSearcher.model.Document;
import com.bw.documentSearcher.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String query) {
        return ResponseEntity.ok(documentService.findByText(query));
    }

    @PostMapping
    public ResponseEntity<Object> saveAll(@RequestBody List<Document> documents) {
        return ResponseEntity.ok(documentService.saveAll(documents));
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(documentService.findAll());
    }
}

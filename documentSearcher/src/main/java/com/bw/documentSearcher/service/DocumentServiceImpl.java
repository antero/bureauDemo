package com.bw.documentSearcher.service;

import com.bw.documentSearcher.model.Document;
import com.bw.documentSearcher.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<Document> findByText(String query) {
        return documentRepository.findAll().stream()
                                    .filter(d -> d.getTitle().contains(query) || d.getBody().contains(query))
                                    .collect(Collectors.toList());
    }

    @Override
    public List<Document> saveAll(List<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}

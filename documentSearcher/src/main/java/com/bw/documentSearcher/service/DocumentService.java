package com.bw.documentSearcher.service;

import com.bw.documentSearcher.model.Document;

import java.util.List;

public interface DocumentService {
    List<Document> findByText(String query);

    List<Document> saveAll(List<Document> documents);
}

package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.Document;

import java.util.List;

public interface DocumentService {
    List<Document> fullTextSearch(String query);

    List<Document> findAll();
}

package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.client.DocumentClient;
import com.bw.translatorCRUD.exception.TranslatorNotFoundException;
import com.bw.translatorCRUD.model.Document;
import com.bw.translatorCRUD.model.TranslatorDetails;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentClient documentClient;

    @Autowired
    private TranslatorService translatorService;

    public DocumentServiceImpl() {
        this.documentClient = Feign.builder()
                                   .client(new OkHttpClient())
                                   .encoder(new GsonEncoder())
                                   .decoder(new GsonDecoder())
                                   .target(DocumentClient.class, "http://localhost:8081/documents");
    }

    @Override
    public List<Document> fullTextSearch(String query) {
        List<Document> documents = documentClient.findByText(query);

        documents.stream()
                .forEach(d -> d.setTranslatorDetails(getTranslatorDetailsOrDefault(d.getTranslatorId())));

        return documents;
    }

    @Override
    public List<Document> findAll() {
        List<Document> documents = documentClient.findAll();

        documents.stream()
                 .forEach(d -> d.setTranslatorDetails(getTranslatorDetailsOrDefault(d.getTranslatorId())));

        return documents;
    }

    private TranslatorDetails getTranslatorDetailsOrDefault(long id) {
        try {
            return TranslatorDetails.of(translatorService.findById(id));
        } catch (TranslatorNotFoundException e) {
            return new TranslatorDetails(id, "not", "found");
        }
    }
}

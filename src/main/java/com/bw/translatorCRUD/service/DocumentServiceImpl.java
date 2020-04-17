package com.bw.translatorCRUD.service;

import com.bw.translatorCRUD.model.Document;
import com.bw.translatorCRUD.model.TranslatorDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public List<Document> fullTextSearch(String query) {
        TranslatorDetails translatorDetails = new TranslatorDetails(1, "Beltrano", "beltrano@mail.com");
        Document d1 = new Document(1, "Title 1", "Body 1", translatorDetails);
        Document d2 = new Document(2, "Title 2", "Body 2", translatorDetails);
        return Arrays.asList(d1, d2);
    }
}

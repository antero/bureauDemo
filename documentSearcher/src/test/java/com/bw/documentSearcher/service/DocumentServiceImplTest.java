package com.bw.documentSearcher.service;

import com.bw.documentSearcher.model.Document;
import com.bw.documentSearcher.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DocumentServiceImplTest {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentService documentService;

    @BeforeAll
    void setUp() {
        Document d1 = new Document(1, "Bureau Platform", "Getting content to your international markets doesn’t need to be disjointed and overwhelming. Bureau Works puts you back in control by aligning your people, content and processes - all in one platform.");
        Document d2 = new Document(1, "Translation Memory", "Imagine every localization project flowing through a single platform, no matter how it was sent. What if you had every language vendor, internal reviewer and project manager working in concert, using the same terminology and leveraging the same translation memories.");
        Document d3 = new Document(2, "Collaboration", "Transparency drives accountability. Thread-based communication is woven into the system and tied directly to decisions. That means no more guessing how a certain translation choices came to be.");
        Document d4 = new Document(3, "Automation", "Bureau Works uses customized tags to automate the assignment of projects to the right talent. Automation algorithms leverage tagging along with deep data intelligence to maximize production efficiency.");

        documentRepository.saveAll(Arrays.asList(d1, d2, d3, d4));
    }

    @Test
    void findByText() {
        List<Document> documents = documentService.findByText("Bureau");
        assertEquals(2, documents.size());
    }
}
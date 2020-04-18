package com.bw.documentSearcher.repository;

import com.bw.documentSearcher.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

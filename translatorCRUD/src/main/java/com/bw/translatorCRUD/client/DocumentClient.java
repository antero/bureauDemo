package com.bw.translatorCRUD.client;

import com.bw.translatorCRUD.model.Document;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface DocumentClient {
    @RequestLine("GET")
    List<Document> findAll();

    @RequestLine("GET /search?query={query}")
    List<Document> findByText(@Param("query") String query);
}

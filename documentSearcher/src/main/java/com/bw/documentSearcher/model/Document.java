package com.bw.documentSearcher.model;

import javax.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Lob
    @Column(columnDefinition = "CLOB")
    private String body;
    private long translatorId;

    public Document() {}

    public Document(long translatorId, String title, String body) {
        this.translatorId = translatorId;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(long translatorId) {
        this.translatorId = translatorId;
    }
}

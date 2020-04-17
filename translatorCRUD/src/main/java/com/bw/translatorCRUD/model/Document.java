package com.bw.translatorCRUD.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "documentId", "title", "body", "translator" })
public class Document {
    private long id;
    private String title;
    private String body;
    private long translatorId;
    private TranslatorDetails translatorDetails;

    public Document() {}

    public Document(long id, String title, String body, TranslatorDetails translatorDetails) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.translatorDetails = translatorDetails;
    }

    @JsonProperty("documentId")
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

    @JsonIgnore
    public long getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(long translatorId) {
        this.translatorId = translatorId;
    }

    @JsonProperty("translator")
    public TranslatorDetails getTranslatorDetails() {
        return translatorDetails;
    }

    public void setTranslatorDetails(TranslatorDetails translatorDetails) {
        this.translatorDetails = translatorDetails;
    }
}

package com.bw.translatorCRUD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Translator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @Email(message = "Email must be a valid email")
    @NotEmpty(message = "Email must not be empty")
    private String email;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime modified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("translators")
    private Set<TranslationSkill> translationSkills;

    public Translator(){
        this.translationSkills = new HashSet<>();
    }

    public Translator(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Translator(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Set<TranslationSkill> getTranslationSkills() {
        return translationSkills;
    }

    public void setTranslationSkills(Set<TranslationSkill> translationSkills) {
        this.translationSkills = translationSkills;
    }
}

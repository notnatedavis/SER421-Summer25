/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\Survey.java
 * JPA Entities
 */
package com.example.surveyapi.model;

// imports
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "surveys")
public class Survey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurveyState state = SurveyState.CREATED;

    @ManyToMany
    @JoinTable(
        name = "survey_items",
        joinColumns = @JoinColumn(name = "survey_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<SurveyItem> items = new HashSet<>();

    protected Survey() {}

    public Survey(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // getters & setters

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public SurveyState getState() { return state; }
    public Set<SurveyItem> getItems() { return items; }

    // domain operations for states
    public void addItem(SurveyItem item) {
        if (state != SurveyState.CREATED) {
            throw new IllegalStateException("DEBUG: only add item(s) if Survey state = CREATED");
        }
        items.add(item);
    }

    public void markCompleted() {
        if (state != SurveyState.CREATED) {
            throw new IllegalStateException("DEBUG: only CREATED Survey can be marked COMPLETE");
        }
        this.state = SurveyState.COMPLETED;
    }

    public void softDelete() {
        this.state = SurveyState.DELETED;
    }
}

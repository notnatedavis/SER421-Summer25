/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\SurveyItemInstance.java
 * JPA Entities
 */
package com.example.surveyapi.model;

// imports
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "survey_item_instances")
public class SurveyItemInstance {

    // declarations
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private SurveyItem item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instance_id")
    private SurveyInstance instance;

    @Column
    private String answerChoice;

    @Column
    private Boolean correct;

    @Column(nullable = false)
    private Boolean completed = false;

    protected SurveyItemInstance() {}

    // constructor
    public SurveyItemInstance(SurveyInstance instance, SurveyItem item) {
        this.instance = instance;
        this.item = item;
    }

    // getters
    public Long getId() { return id; }
    public SurveyItem getItem() { return item; }
    public String getAnswerChoice() { return answerChoice; }
    public Boolean isCorrect() { return correct; }
    public Boolean isCompleted() { return completed; }

    // method
    public void answer(String answerChoice) {
        if (completed) throw new IllegalStateException("Already answered");
        this.answerChoice = answerChoice;
        this.correct = item.getCorrectAnswer().equals(answerChoice);
        this.completed = true;
    }
}
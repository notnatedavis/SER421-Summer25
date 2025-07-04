/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\Question.java
 * JPA Entities
 */
package com.example.surveyapi.model;

// imports
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Question {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    protected Question() {}            // JPA
    
    public Question(String text, QuestionType type, Survey survey) {
        this.text = text;
        this.type = type;
        this.survey = survey;
    }

    // gettes & setters

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

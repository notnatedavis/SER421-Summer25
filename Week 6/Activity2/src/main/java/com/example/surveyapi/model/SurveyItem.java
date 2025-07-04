/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\SurveyItem.java
 * JPA Entities
 */
package com.example.surveyapi.model;

// imports
import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class SurveyItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionStem;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ElementCollection
    @CollectionTable(name = "survey_item_candidates", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "candidate")
    private List<String> candidates;

    private String correctAnswer;

    // JPA needs a no-arg constructor
    protected SurveyItem() { }

    public SurveyItem(String questionStem, QuestionType type, List<String> candidates, String correctAnswer) {
        this.questionStem = questionStem;
        this.type = type;
        this.candidates = List.copyOf(candidates);
        this.correctAnswer = correctAnswer;
    }

    // getters
    public Long getId() { return id; }
    public String getQuestionStem() { return questionStem; }
    public QuestionType getType() { return type; }
    public List<String> getCandidates() { return candidates; }
    public String getCorrectAnswer() { return correctAnswer; }
}
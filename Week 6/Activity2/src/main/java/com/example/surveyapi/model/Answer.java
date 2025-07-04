/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\Answer.java
 * JPA Entities
 */
package com.example.surveyapi.model;

// imports
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; // not used
import jakarta.persistence.CascadeType; // not used

@Entity
public class Answer {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String response;

    @ManyToOne(optional = false)
    private Question question;

    protected Answer() { } // JPA

    public Answer(String response, Question question) {
        this.response = response;
        this.question = question;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\SurveyItemInstanceDTO.java
 * UPDATE
 */
package com.example.surveyapi.dto;

// imports
import com.example.surveyapi.model.SurveyItemInstance;

public class SurveyItemInstanceDTO {

    // declarations
    private Long id;
    private SurveyItemDTO item;
    private String answerChoice;
    private Boolean correct;
    private Boolean completed;

    // constructor
    public SurveyItemInstanceDTO(SurveyItemInstance inst) {
        this.id = inst.getId();
        this.item = new SurveyItemDTO(inst.getItem());
        this.answerChoice = inst.getAnswerChoice();
        this.correct = inst.isCorrect();
        this.completed = inst.isCompleted();
    }
    
    // getters

    public Long getId() {
        return id;
    }

    public SurveyItemDTO getItem() {
        return item;
    }

    public String getAnswerChoice() {
        return answerChoice;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public Boolean getCompleted() {
        return completed;
    }
}
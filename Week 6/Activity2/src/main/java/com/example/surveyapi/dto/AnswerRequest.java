/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\AnswerRequest.java
 * Request payload for answering a SurveyItemInstance
 */
package com.example.surveyapi.dto;

// imports
import jakarta.validation.constraints.NotBlank;

public class AnswerRequest {

    // declaration
    @NotBlank(message = "Answer choice can not be blank")
    private String answerChoice;

    // constructor
    public AnswerRequest() {}

    // getters & setters

    public String getAnswerChoice() {
        return answerChoice;
    }

    public void setAnswerChoice(String answerChoice) {
        this.answerChoice = answerChoice;
    }
}
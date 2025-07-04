/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\CreateQuestionRequest.java
 * Request payload for creating a Question
 */
package com.example.surveyapi.dto;

// imports
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.example.surveyapi.model.QuestionType;

public class CreateQuestionRequest {

    // declarations
    @NotBlank(message = "Text must not be blank")
    private String text;

    @NotNull(message = "Question type must be provided")
    private QuestionType type;

    @NotNull(message = "Survey ID must be provided")
    private Long surveyId;

    // constructor
    public CreateQuestionRequest() {}

    // getters & setters

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

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }
}
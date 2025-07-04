/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\QuestionDTO.java
 * Response DTO for Question entities
 */
package com.example.surveyapi.dto;

// imports
import com.example.surveyapi.model.Question;
import com.example.surveyapi.model.Survey; // not used ?

public class QuestionDTO {

    // declarations
    private Long id;
    private String text;
    private String type;
    private Long surveyId;

    // constructor
    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.type = question.getType().name();
        this.surveyId = (question.getSurvey() != null ? question.getSurvey().getId() : null);
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public Long getSurveyId() {
        return surveyId;
    }
}
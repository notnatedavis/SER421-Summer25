/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\AnswerDTO.java
 * Response DTO for Answer entities
 */
package com.example.surveyapi.dto;

// imports
import com.example.surveyapi.model.Answer;

public class AnswerDTO {

    // declarations
    private Long id;
    private String response;
    private Long questionId;

    // constructor
    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.response = answer.getResponse();
        this.questionId = answer.getQuestion().getId();
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public Long getQuestionId() {
        return questionId;
    }
}

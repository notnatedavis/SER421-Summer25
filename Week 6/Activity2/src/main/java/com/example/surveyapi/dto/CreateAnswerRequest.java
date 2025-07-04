/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\CreateAnswerRequest.java
 * Request payload for creating an Answer
 */
package com.example.surveyapi.dto;

// imports
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAnswerRequest {

    // declarations
    @NotBlank(message = "Response must not be blank")
    private String response;

    @NotNull(message = "Question ID must be provided")
    private Long questionId;

    // constructor
    public CreateAnswerRequest() {}

    // getters & setters
    
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
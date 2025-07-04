/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\CreateInstanceRequest.java
 * UPDATE
 */
package com.example.surveyapi.dto;

// imports
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateInstanceRequest {
    
    // declarations
    @NotNull
    private Long surveyId;

    @NotBlank
    private String userName;

    // getters & setters

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
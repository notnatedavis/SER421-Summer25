/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\CreateSurveyRequest.java
 * Request payload for creating a new Survey
 */
package com.example.surveyapi.dto;

// imports
import jakarta.validation.constraints.NotBlank;

public class CreateSurveyRequest {

    // declarations
    @NotBlank(message = "Title must not be blank")
    private String title;
    private String description;

    // constructor
    public CreateSurveyRequest() {}

    // getters & setters
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

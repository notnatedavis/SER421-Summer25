/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\SurveyDTO.java
 * api response DTO
 */
package com.example.surveyapi.dto;

// imports
import com.example.surveyapi.model.Survey;
import com.example.surveyapi.model.SurveyState;
import java.util.Set;
import java.util.stream.Collectors;

public class SurveyDTO {
    
    // declarations
    private Long id;
    private String title;
    private String description;
    private SurveyState state;
    private Set<SurveyItemDTO> items;

    // constructor
    public SurveyDTO(Survey survey) {
        this.id = survey.getId();
        this.title = survey.getTitle();
        this.description = survey.getDescription();
        this.state = survey.getState();
        this.items = survey.getItems().stream().map(SurveyItemDTO::new).collect(Collectors.toSet());
    }

    // getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public SurveyState getState() { return state; }
    public Set<SurveyItemDTO> getItems() { return items; }
}

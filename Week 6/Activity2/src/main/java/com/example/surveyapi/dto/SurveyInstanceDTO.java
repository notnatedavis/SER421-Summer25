/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\SurveyInstanceDTO.java
 */
package com.example.surveyapi.dto;

// imports
import com.example.surveyapi.model.SurveyInstance;
import com.example.surveyapi.model.SurveyInstanceState;
import java.util.List;
import java.util.stream.Collectors;

public class SurveyInstanceDTO {

    // declarations
    private Long id;
    private SurveyDTO survey;
    private String userName;
    private SurveyInstanceState state;
    private List<SurveyItemInstanceDTO> items;

    // constructor
    public SurveyInstanceDTO(SurveyInstance inst) {
        this.id = inst.getId();
        this.survey = new SurveyDTO(inst.getSurvey());
        this.userName = inst.getUserName();
        this.state = inst.getState();
        this.items = inst.getItemInstances().stream().map(SurveyItemInstanceDTO::new).collect(Collectors.toList());
    }

    // getters 

    public Long getId() {
        return id;
    }

    public SurveyDTO getSurvey() {
        return survey;
    }

    public String getUserName() {
        return userName;
    }

    public SurveyInstanceState getState() {
        return state;
    }

    public List<SurveyItemInstanceDTO> getItems() {
        return items;
    }
}
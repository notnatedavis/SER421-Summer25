/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\SurveyInstance.java
 * JPA Entities
 */
package com.example.surveyapi.model;

// imports
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "survey_instances")
public class SurveyInstance {

    // declarations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurveyInstanceState state = SurveyInstanceState.CREATED;

    @OneToMany(mappedBy = "instance", cascade = CascadeType.ALL)
    private List<SurveyItemInstance> itemInstances = new ArrayList<>();

    protected SurveyInstance() {}

    // constructor
    public SurveyInstance(Survey survey, String userName) {
        this.survey = survey;
        this.userName = userName;
        // clone items
        survey.getItems().forEach(item -> {
            SurveyItemInstance inst = new SurveyItemInstance(this, item);
            this.itemInstances.add(inst);
        });
    }

    // getters
    public Long getId() { return id; }
    public Survey getSurvey() { return survey; }
    public String getUserName() { return userName; }
    public SurveyInstanceState getState() { return state; }
    public List<SurveyItemInstance> getItemInstances() { return itemInstances; }

    // methods
    public void recordAnswer(Long itemInstId, String answerChoice) {
        itemInstances.stream().filter(i -> i.getId().equals(itemInstId)).findFirst().orElseThrow(() -> new IllegalArgumentException("Item instance not found")).answer(answerChoice);
        
        // update state
        boolean anyAnswered = itemInstances.stream().anyMatch(SurveyItemInstance::isCompleted);
        boolean allAnswered = itemInstances.stream().allMatch(SurveyItemInstance::isCompleted);
        
        if (allAnswered) state = SurveyInstanceState.COMPLETED;
        else if (anyAnswered) state = SurveyInstanceState.IN_PROGRESS;
    }
}

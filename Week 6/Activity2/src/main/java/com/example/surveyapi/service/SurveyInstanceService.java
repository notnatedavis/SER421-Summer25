/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\service\SurveyInstanceService.java
 * Logic for Survey Instance(s) Service
 */
package com.example.surveyapi.service;

// imports
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.example.surveyapi.model.*;
import com.example.surveyapi.repository.*;
import java.util.List;

@Service
public class SurveyInstanceService {

    // declarations
    private final SurveyRepository surveyRepo;
    private final SurveyInstanceRepository instRepo;

    // constructor
    @Autowired
    public SurveyInstanceService(SurveyRepository surveyRepo, SurveyInstanceRepository instRepo) {
        this.surveyRepo = surveyRepo;
        this.instRepo = instRepo;
    }

    // helpers
    
    @Transactional
    public SurveyInstance createInstance(Long surveyId, String userName) {
        Survey survey = surveyRepo.findById(surveyId).orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        if (survey.getState() != SurveyState.COMPLETED) {
            throw new IllegalStateException("Cannot start instance on non-COMPLETED survey");
        }
        SurveyInstance inst = new SurveyInstance(survey, userName);
        
        return instRepo.save(inst);
    }

    @Transactional
    public SurveyInstance answerItem(Long instId, Long itemInstId, String answerChoice) {
        SurveyInstance inst = instRepo.findById(instId).orElseThrow(() -> new IllegalArgumentException("Instance not found"));
        if (inst.getState() == SurveyInstanceState.COMPLETED) {
            throw new IllegalStateException("Instance already completed");
        }
        inst.recordAnswer(itemInstId, answerChoice);
        
        return inst;
    }

    // getters 

    public List<SurveyInstance> getInstances(SurveyInstanceState state) {
        if (state != null) return instRepo.findByState(state);
        
        return instRepo.findAll();
    }

    public SurveyInstance getInstanceById(Long id) {
        return instRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Instance not found"));
    }
}
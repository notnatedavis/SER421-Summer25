/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\service\SurveyService.java
 * Logic for Survey Service
 */
package com.example.surveyapi.service;

// imports
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.example.surveyapi.model.Survey;
import com.example.surveyapi.model.SurveyItem;
import com.example.surveyapi.repository.SurveyRepository;
import com.example.surveyapi.repository.SurveyItemRepository;

@Service
public class SurveyService {
    
    // declarations
    private final SurveyRepository surveyRepo;
    private final SurveyItemRepository itemRepo;

    @Autowired
    public SurveyService(SurveyRepository surveyRepo, SurveyItemRepository itemRepo) {
        this.surveyRepo = surveyRepo;
        this.itemRepo = itemRepo;
    }

    public Survey createSurvey(Survey s) {
        return surveyRepo.save(s);
    }

    public List<Survey> getAllSurveys() {
        return surveyRepo.findAll();
    }

    public Survey getSurvey(Long id) {
        return surveyRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Survey not found"));
    }

    @Transactional
    public Survey addItemToSurvey(Long surveyId, Long itemId) {
        Survey survey = getSurvey(surveyId);
        SurveyItem item = itemRepo.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));
        survey.addItem(item);
        
        return survey;
    }

    @Transactional
    public Survey markSurveyCompleted(Long surveyId) {
        Survey survey = getSurvey(surveyId);
        survey.markCompleted();
        
        return survey;
    }

    @Transactional
    public Survey deleteSurvey(Long surveyId) {
        Survey survey = getSurvey(surveyId);
        survey.softDelete();
        
        return survey;
    }
}
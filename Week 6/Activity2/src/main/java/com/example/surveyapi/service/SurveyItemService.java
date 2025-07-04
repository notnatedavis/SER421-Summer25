/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\service\SurveyItemService.java
 * Logic for Survey Item(s) Service
 */
package com.example.surveyapi.service;

// imports
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.example.surveyapi.model.SurveyItem;
import com.example.surveyapi.repository.SurveyItemRepository;

@Service
public class SurveyItemService {
    
    @Autowired
    private SurveyItemRepository itemRepo;

    public SurveyItem createSurveyItem(SurveyItem item) {
        return itemRepo.save(item);
    }

    // getters
    
    public List<SurveyItem> getAllItems() {
        return itemRepo.findAll();
    }

    public Optional<SurveyItem> getItemById(Long id) {
        return itemRepo.findById(id);
    }
}
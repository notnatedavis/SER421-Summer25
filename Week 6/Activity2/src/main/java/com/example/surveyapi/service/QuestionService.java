/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\service\QuestionService.java
 * Logic for Question(s) Service
 */
package com.example.surveyapi.service;

// imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.surveyapi.model.Question;
import com.example.surveyapi.model.Survey;
import com.example.surveyapi.repository.QuestionRepository;
import com.example.surveyapi.repository.SurveyRepository;

import com.example.surveyapi.dto.CreateQuestionRequest;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    // declarations
    private final QuestionRepository questionRepo;
    private final SurveyRepository surveyRepo;

    // constructor
    @Autowired
    public QuestionService(QuestionRepository questionRepo, SurveyRepository surveyRepo) {
        this.questionRepo = questionRepo;
        this.surveyRepo = surveyRepo;
    }

    // helper methods
    @Transactional
    public Question createQuestion(CreateQuestionRequest req) {
        Survey survey = surveyRepo.findById(req.getSurveyId()).orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        Question question = new Question(req.getText(), req.getType(), survey);
        
        return questionRepo.save(question);
    }


    @Transactional
    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }

    // getters

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepo.findById(id);
    }
}
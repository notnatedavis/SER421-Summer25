/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\service\AnswerService.java
 * Logic for Answer(s) Service
 */
package com.example.surveyapi.service;

// imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.surveyapi.model.Answer;
import com.example.surveyapi.model.Question;
import com.example.surveyapi.repository.AnswerRepository;
import com.example.surveyapi.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    
    // declarations
    private final AnswerRepository answerRepo;
    private final QuestionRepository questionRepo;

    // constructor
    @Autowired
    public AnswerService(AnswerRepository answerRepo, QuestionRepository questionRepo) {
        this.answerRepo = answerRepo;
        this.questionRepo = questionRepo;
    }

    // helper methods
    
    @Transactional
    public Answer createAnswer(String response, Long questionId) {
        Question q = questionRepo.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        Answer a = new Answer(response, q);
        
        return answerRepo.save(a);
    }

    @Transactional
    public void deleteAnswer(Long id) {
        answerRepo.deleteById(id);
    }

    // getters

    public List<Answer> getAllAnswers() {
        return answerRepo.findAll();
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepo.findById(id);
    }
}
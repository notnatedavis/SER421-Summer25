/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\SurveyItemDTO.java
 * Response DTO exposing SurveyItem data
 */
package com.example.surveyapi.dto;

// imports
import com.example.surveyapi.model.SurveyItem;
import com.example.surveyapi.model.QuestionType;
import java.util.List;

public class SurveyItemDTO {

    // declarations
    private Long id;
    private String questionStem;
    private QuestionType type;
    private List<String> candidates;
    private String correctAnswer;

    // constructor
    public SurveyItemDTO(SurveyItem item) {
        this.id = item.getId();
        this.questionStem = item.getQuestionStem();
        this.type = item.getType();
        this.candidates = item.getCandidates();
        this.correctAnswer = item.getCorrectAnswer();
    }

    // getters only

    public Long getId() {
        return id;
    }

    public String getQuestionStem() {
        return questionStem;
    }

    public QuestionType getType() {
        return type;
    }

    public List<String> getCandidates() {
        return candidates;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
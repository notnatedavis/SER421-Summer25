/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\controller\QuestionController.java
 * REST Controller for Question(s)
 */
package com.example.surveyapi.controller;

// imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import com.example.surveyapi.dto.CreateQuestionRequest;
import com.example.surveyapi.dto.QuestionDTO;

import com.example.surveyapi.dto.*;
import com.example.surveyapi.model.Question;
import com.example.surveyapi.service.QuestionService;
import com.example.surveyapi.dto.ApiResponse;

@RestController
@RequestMapping("/api/questions")
@Validated
public class QuestionController {

    // declaration
    private final QuestionService service;

    // constructor
    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }

    // helper methods

    // POST /api/questions
    @PostMapping
    public ResponseEntity<ApiResponse<QuestionDTO>> create(@Valid @RequestBody CreateQuestionRequest req) {
        Question q = service.createQuestion(req);
        return ResponseEntity.status(201).body(new ApiResponse<>(new QuestionDTO(q)));
    }

    // GET /api/questions
    @GetMapping
    public ResponseEntity<ApiResponse<List<QuestionDTO>>> listAll() {
        List<QuestionDTO> dtos = service.getAllQuestions().stream()
                .map(QuestionDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(dtos));
    }

    // GET /api/questions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionDTO>> getOne(@PathVariable Long id) {
        return service.getQuestionById(id)
                .map(q -> ResponseEntity.ok(new ApiResponse<>(new QuestionDTO(q))))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/questions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
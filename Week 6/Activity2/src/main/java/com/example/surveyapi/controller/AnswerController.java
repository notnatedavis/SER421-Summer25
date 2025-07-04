/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\controller\AnswerController.java
 * REST Controller for Answer(s)
 */
package com.example.surveyapi.controller;

// imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import com.example.surveyapi.dto.AnswerDTO;
import com.example.surveyapi.dto.ApiResponse;
import com.example.surveyapi.dto.CreateAnswerRequest;

import com.example.surveyapi.dto.*;
import com.example.surveyapi.model.Answer;
import com.example.surveyapi.service.AnswerService;

@RestController
@RequestMapping("/api/answers")
@Validated
public class AnswerController {

    // declaration
    private final AnswerService service;

    // constructor
    @Autowired
    public AnswerController(AnswerService service) {
        this.service = service;
    }

    // helper methods

    // create a new Answer
    @PostMapping
    public ResponseEntity<ApiResponse<AnswerDTO>> create(@Valid @RequestBody CreateAnswerRequest req) {
        Answer created = service.createAnswer(req.getResponse(), req.getQuestionId());
        AnswerDTO dto = new AnswerDTO(created);
        URI location = URI.create("/api/answers/" + created.getId());
        
        return ResponseEntity.created(location).body(new ApiResponse<>(dto));
    }

    
    // GET /api/answers
    @GetMapping
    public ApiResponse<List<AnswerDTO>> listAll() {
        List<AnswerDTO> dtos = service.getAllAnswers().stream().map(AnswerDTO::new).collect(Collectors.toList());
        
        return new ApiResponse<>(dtos);
    }
    
    // GET /api/answers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnswerDTO>> getOne(@PathVariable Long id) {
        return service.getAnswerById(id).map(a -> ResponseEntity.ok(new ApiResponse<>(new AnswerDTO(a)))).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/answers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAnswer(id);
        
        return ResponseEntity.noContent().build();
    }
}
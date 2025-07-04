/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\controller\SurveyController.java
 * REST Controller for Survey
 */
package com.example.surveyapi.controller;

// imports
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import com.example.surveyapi.model.Survey;
import com.example.surveyapi.dto.*;
import com.example.surveyapi.service.SurveyService;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    // declaration
    private final SurveyService service;

    // constructor
    @Autowired
    public SurveyController(SurveyService service) {
        this.service = service;
    }

    // helper methods

    @PostMapping
    public ResponseEntity<ApiResponse<SurveyDTO>> create(@Valid @RequestBody CreateSurveyRequest req) {
        Survey s = new Survey(req.getTitle(), req.getDescription());
        Survey created = service.createSurvey(s);
        URI loc = URI.create("/api/surveys/" + created.getId());

        ApiResponse<SurveyDTO> response = new ApiResponse<>(new SurveyDTO(created));
        
        return ResponseEntity.created(loc).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SurveyDTO>>> listAll() {
        List<SurveyDTO> surveys = service.getAllSurveys().stream().map(SurveyDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(surveys));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyDTO>> getOne(@PathVariable Long id) {
        try {
            Survey s = service.getSurvey(id);

            return ResponseEntity.ok(new ApiResponse<>(new SurveyDTO(s)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Survey not found"));
        }
    }

    @PostMapping("/{surveyId}/items")
    public ResponseEntity<ApiResponse<SurveyDTO>> addItem(@PathVariable Long surveyId, @Valid @RequestBody AddItemRequest req) {
        Survey updated = service.addItemToSurvey(surveyId, req.getItemId());
        
        return ResponseEntity.ok(new ApiResponse<>(new SurveyDTO(updated)));
    }

    @PatchMapping("/{surveyId}/complete")
    public ResponseEntity<ApiResponse<SurveyDTO>> complete(@PathVariable Long surveyId) {
        Survey updated = service.markSurveyCompleted(surveyId);
        
        return ResponseEntity.ok(new ApiResponse<>(new SurveyDTO(updated)));
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long surveyId) {
        service.deleteSurvey(surveyId);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>((Void) null));
    }
}
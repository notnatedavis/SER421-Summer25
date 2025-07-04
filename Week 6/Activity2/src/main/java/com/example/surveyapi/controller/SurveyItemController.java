/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\controller\SurveyItemController.java
 * REST Controller for Survey Items
 */
package com.example.surveyapi.controller;

// imports
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.surveyapi.service.SurveyItemService;
import com.example.surveyapi.dto.ApiResponse;
import com.example.surveyapi.dto.CreateSurveyItemRequest;
import com.example.surveyapi.dto.SurveyItemDTO;
import com.example.surveyapi.model.SurveyItem;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class SurveyItemController {

    // declarations
    @Autowired
    private SurveyItemService itemService;

    @PostMapping
    public ResponseEntity<ApiResponse<SurveyItemDTO>> create(@Valid @RequestBody CreateSurveyItemRequest req) {
        SurveyItem entity = new SurveyItem(req.getQuestionStem(), req.getType(), req.getCandidates(), req.getCorrectAnswer());
        SurveyItem created = itemService.createSurveyItem(entity);
        SurveyItemDTO dto = new SurveyItemDTO(created);

        URI loc = URI.create("/api/items/" + created.getId());
        
        return ResponseEntity.created(loc).body(new ApiResponse<>(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SurveyItemDTO>>> getAll() {
        List<SurveyItemDTO> items = itemService.getAllItems().stream().map(SurveyItemDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyItemDTO>> getOne(@PathVariable Long id) {
        return itemService.getItemById(id).map(i -> new SurveyItemDTO(i)).map(dto -> ResponseEntity.ok(new ApiResponse<>(dto))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Survey item not found")));
    }
}
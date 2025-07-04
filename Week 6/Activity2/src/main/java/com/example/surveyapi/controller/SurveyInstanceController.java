/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\controller\SurveyInstanceController.java
 * REST Controller for Survey Instance
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
import com.example.surveyapi.service.SurveyInstanceService;
import com.example.surveyapi.model.SurveyInstance;
import com.example.surveyapi.dto.*;
import com.example.surveyapi.model.SurveyInstanceState;

@RestController
@RequestMapping("/api/instances")
public class SurveyInstanceController {

    // declaration
    private final SurveyInstanceService service;

    // constructor
    @Autowired
    public SurveyInstanceController(SurveyInstanceService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ApiResponse<SurveyInstanceDTO>> create(@Valid @RequestBody CreateInstanceRequest req) {
        SurveyInstance inst = service.createInstance(req.getSurveyId(), req.getUserName());
        URI location = URI.create("/api/instances/" + inst.getId());
        
        return ResponseEntity.created(location).body(new ApiResponse<>(new SurveyInstanceDTO(inst)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SurveyInstanceDTO>>> list(@RequestParam(required = false) SurveyInstanceState state) {
        List<SurveyInstanceDTO> list = service.getInstances(state).stream().map(SurveyInstanceDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyInstanceDTO>> getOne(@PathVariable Long id) {
        try {
            SurveyInstanceDTO dto = new SurveyInstanceDTO(service.getInstanceById(id));
            
            return ResponseEntity.ok(new ApiResponse<>(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Survey instance not found"));
        }
    }

    @PostMapping("/{instId}/items/{itemInstId}/answer")
    public ResponseEntity<ApiResponse<SurveyInstanceDTO>> answer(@PathVariable Long instId, @PathVariable Long itemInstId, @Valid @RequestBody AnswerRequest req) {
        SurveyInstance inst = service.answerItem(instId, itemInstId, req.getAnswerChoice());
        
        return ResponseEntity.ok(new ApiResponse<>(new SurveyInstanceDTO(inst)));
    }
}
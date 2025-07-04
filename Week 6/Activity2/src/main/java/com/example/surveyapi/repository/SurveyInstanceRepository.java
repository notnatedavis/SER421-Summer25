/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\repository\SurveyInstanceRepository.java
 * Spring Data JPA Repository for Survey Instance entities
 */
package com.example.surveyapi.repository;

// imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.surveyapi.model.SurveyInstance;
import com.example.surveyapi.model.SurveyInstanceState;

import java.util.List;

@Repository
public interface SurveyInstanceRepository extends JpaRepository<SurveyInstance, Long> {
    List<SurveyInstance> findByState(SurveyInstanceState state);
}
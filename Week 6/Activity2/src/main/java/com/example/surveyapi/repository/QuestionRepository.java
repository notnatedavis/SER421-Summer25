/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\repository\QuestionRepository.java
 * Spring Data JPA Repository for Question entities
 */
package com.example.surveyapi.repository;

// imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.example.surveyapi.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> { }
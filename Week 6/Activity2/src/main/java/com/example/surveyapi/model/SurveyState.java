/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\model\SurveyState.java
 * States of a Survey :
 * CREATED - can add items
 * COMPLETED - ready for instances
 * DELETED - soft delete
 */
package com.example.surveyapi.model;

public enum SurveyState {
    CREATED,
    COMPLETED,
    DELETED
}
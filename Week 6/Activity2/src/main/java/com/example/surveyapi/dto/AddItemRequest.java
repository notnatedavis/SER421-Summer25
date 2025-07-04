/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\AddItemRequest.java
 * Request payload for adding existing SurveyItem to a Survey
 */
package com.example.surveyapi.dto;

public class AddItemRequest {
    private Long itemId;

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
}
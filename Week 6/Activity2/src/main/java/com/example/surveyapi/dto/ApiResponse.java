/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/4/25
 * 
 * src\main\java\com\example\surveyapi\dto\ApiResponse.java
 * Wrapper for API responses
 * Supports payload or error + HATEOS links
 */
package com.example.surveyapi.dto;

// imports
import java.util.List;

public class ApiResponse<T> {

    // declarations
    private T data;
    private String error;
    
    // constructor
    public ApiResponse() {}

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(String error) {
        this.error = error;
    }

    // getters & setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

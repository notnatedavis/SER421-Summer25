/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\output\DeleteBookByISBNPayload.java
 * defines output type for deleteBookByISBN and what to return if success
 */
package com.example.graphqlserver.dto.output;

// record generates constructor
public record DeleteBookByISBNPayload(
    String isbn
) {}
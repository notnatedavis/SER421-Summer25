/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\input\DeleteBookByISBNInput.java
 * defines input DTO for when deleting a book by ISBN
 */
package com.example.graphqlserver.dto.input;

// record generates constructor
public record DeleteBookByISBNInput(
    String isbn
) {}

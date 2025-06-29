/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\input\AddBookInput.java
 * defines input object when requesting to add new book
 */
package com.example.graphqlserver.dto.input;

// record generates constructor
public record AddBookInput(String isbn, String title, int authorId) {
}

/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\input\AddBookInput.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.dto.input;

public record AddBookInput(String isbn, String title, int authorId) {
}

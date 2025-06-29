/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\output\AddAuthorPayload.java
 * defines output for returning new Author from addAuthor
 */
package com.example.graphqlserver.dto.output;

// imports
import com.example.graphqlserver.model.Author;

// record generates constructor
public record AddAuthorPayload(Author author) {
}

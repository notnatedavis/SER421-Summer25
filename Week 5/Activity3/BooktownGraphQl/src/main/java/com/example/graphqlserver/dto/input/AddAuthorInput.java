/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\input\AddAuthorInput.java
 * defines input type for addAuthor mutation
 */
package com.example.graphqlserver.dto.input;

// imports
import com.example.graphqlserver.model.Book;

import java.util.List;

// record generates constructor
public record AddAuthorInput(String firstName, String lastName) {
}

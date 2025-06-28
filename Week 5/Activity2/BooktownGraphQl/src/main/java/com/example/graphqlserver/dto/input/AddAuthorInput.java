/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\input\AddAuthorInput.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.dto.input;

// imports
import com.example.graphqlserver.model.Book;

import java.util.List;

public record AddAuthorInput(String firstName, String lastName) {
}

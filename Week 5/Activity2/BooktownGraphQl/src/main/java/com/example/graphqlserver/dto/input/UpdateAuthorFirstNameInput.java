/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\input\UpdateAuthorFirstNameInput.java
 * defines input type for mutation of updating author's firstName
 */
package com.example.graphqlserver.dto.input;

// record generates constructor
public record UpdateAuthorFirstNameInput(
    String authorId,
    String newFirstName
) {}
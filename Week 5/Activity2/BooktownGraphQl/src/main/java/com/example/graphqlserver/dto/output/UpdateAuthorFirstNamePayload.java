/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\output\UpdateAuthorFirstNamePayload.java
 * defines output for updateAuthorFirstName mutation and what to return if success
 */
package com.example.graphqlserver.dto.output;

// record generates constructor
public record UpdateAuthorFirstNamePayload(
    String oldFirstName
) {}

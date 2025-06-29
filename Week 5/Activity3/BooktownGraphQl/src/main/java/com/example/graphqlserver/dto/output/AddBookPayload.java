/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\dto\output\AddBookPayload.java
 * defines response used by addBook (after added + linked)
 */
package com.example.graphqlserver.dto.output;

// imports
import com.example.graphqlserver.model.Book;

// record generates constructor
public record AddBookPayload(Book book) {
}

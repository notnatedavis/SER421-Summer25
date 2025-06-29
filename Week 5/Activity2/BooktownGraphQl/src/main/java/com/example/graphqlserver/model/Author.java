/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\model\Author.java
 * provides structure of Author model
 */
package com.example.graphqlserver.model;

// imports
import java.util.ArrayList;
import java.util.List;

public class Author {

    // declarations
    private int id;
    private String firstName; // had to remove 'final'
    private String lastName; // had to remove 'final'
    private List<Book> books = new ArrayList<>();

    // constructor
    public Author(int id, String firstName, String lastName, List<Book> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    // new method for setting firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

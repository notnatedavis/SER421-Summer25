/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\model\Author.java
 * provides structure of Author model
 */
package com.example.graphqlserver.model;

// imports
import jakarta.persistence.*;

import java.util.List;

import javax.annotation.processing.Generated;

@Entity // JPA entity
@Table(name = "authors") // map entity to 'authors'
public class Author { // represents an author

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // fields (first & last name)
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // relationships (bidirectional)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Author() { // required by JPA for entity creation
        // no arg constructor
    }

    // constructor
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

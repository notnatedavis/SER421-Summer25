/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\model\Book.java
 * provides structure of Book model
 */
package com.example.graphqlserver.model;

// imports
import jakarta.persistence.*;

@Entity // JPA entity
@Table(name = "books") // map entity to 'books'
public class Book { // represents a book

    // primary key
    @Id

    // fields (isbn & title)
    @Column(length = 20)
    private String isbn;

    @Column(nullable = false)
    private String title;

    // relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    @JoinColumn(name = "author_id")
    private Author author;

    public Book() { // required by JPA for entity creation
        // no arg constructor
    }

    // constructor
    public Book(String isbn, String title, Author author) {
        this.isbn   = isbn;
        this.title  = title;
        this.author = author;
    }

    // getters & setters

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
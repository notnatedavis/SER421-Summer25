/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\model\Book.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.model;

// imports
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(length = 20)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {
        // no arg constructor
    }

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
/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\controller\BookController.java
 * handles queries + mutations related to Author
 */
package com.example.graphqlserver.controller;

// imports
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import java.util.List;

import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.services.BookService;
import com.example.graphqlserver.model.Book;

import com.example.graphqlserver.dto.input.DeleteBookByISBNInput;
import com.example.graphqlserver.dto.output.DeleteBookByISBNPayload;

@Controller
public class BookController {

    private final BookService bookSvc;
    public BookController(BookService bookSvc) {
        this.bookSvc = bookSvc;
    }

    // query all book(s)
    @QueryMapping public List<Book> books() {
        return bookSvc.findAll();
    }

    // query all book(s) by isbn
    @QueryMapping public Book bookByISBN(@Argument String isbn) {
        return bookSvc.findByIsbn(isbn);
    }

    // mutation for adding a new book
    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput in) {
        Book saved = bookSvc.save(in.isbn(), in.title(), in.authorId());
        return new AddBookPayload(saved);
    }

    // list of book(s) based on authorId
    @QueryMapping
    public List<Book> booksByAuthorId(@Argument Integer authorId) {
        return bookSvc.findByAuthorId(authorId);
    }

    // mutation for deleting a book by isbn
    @MutationMapping
    public DeleteBookByISBNPayload deleteBookByISBN(@Argument DeleteBookByISBNInput in) {
        
        boolean existed = bookSvc.existsByIsbn(in.isbn());

        if (existed) {
            bookSvc.deleteByIsbn(in.isbn());
            return new DeleteBookByISBNPayload(in.isbn());
        }
        return new DeleteBookByISBNPayload(null);
    }
}
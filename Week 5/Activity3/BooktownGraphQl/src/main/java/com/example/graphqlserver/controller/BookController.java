/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\controller\BookController.java
 * [PURPOSE_OF_FILE_IN_RELATION]
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

    @QueryMapping public List<Book> books() {
        return bookSvc.findAll();
    }

    @QueryMapping public Book bookByISBN(@Argument String isbn) {
        return bookSvc.findByIsbn(isbn);
    }

    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput in) {
        Book saved = bookSvc.save(in.isbn(), in.title(), in.authorId());
        return new AddBookPayload(saved);
    }

    @QueryMapping
    public List<Book> booksByAuthorId(@Argument Integer authorId) {
        return bookSvc.findByAuthorId(authorId);
    }

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
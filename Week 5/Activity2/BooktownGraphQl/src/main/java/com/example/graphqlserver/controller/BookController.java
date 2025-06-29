/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\controller\BookController.java
 * handles queries and mutations related to Book + BookRepository
 */
package com.example.graphqlserver.controller;

// imports
import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.dto.input.DeleteBookByISBNInput; // new import
import com.example.graphqlserver.dto.output.DeleteBookByISBNPayload; // new import
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // query all book(s)
    @QueryMapping
    public List<Book> books() {
        return bookRepository.getBooks();
    }

    // query all book(s) by isbn
    @QueryMapping
    public  Book bookByISBN(@Argument("isbn") String isbn) {
        return bookRepository.getBookByISBN(isbn);
    }

    // mutation for adding a new book
    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput input) {
        Author author = authorRepository.getAuthorById(input.authorId());
        if (author == null) {
            throw  new IllegalArgumentException("Author with ID " + input.authorId() + "does not exist");
        }
        var book = bookRepository.save(input.isbn(), input.title(), input.authorId());
        author.getBooks().add(book);
        var out = new AddBookPayload(book);
        return out;
    }

    // Task 1 , list of Book(s) based on authorId
    @QueryMapping
    public List<Book> booksByAuthorId(@Argument Integer authorId) {
        return bookRepository.findByAuthorId(authorId);
    } 
    
    // Task 4 , delete a Book given it's ISBN
    @MutationMapping
    public DeleteBookByISBNPayload deleteBookByISBN(@Argument DeleteBookByISBNInput input) {

        boolean exist = bookRepository.existsById(input.isbn());

        if (exist) {
            bookRepository.deleteById(input.isbn());
            return new DeleteBookByISBNPayload(input.isbn());
        } else {
            return new DeleteBookByISBNPayload(null);
        }
    }
}

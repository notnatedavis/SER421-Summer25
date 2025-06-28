// src\main\java\com\example\graphqlserver\services\BookService.java

package com.example.graphqlserver.services;

import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.BookRepository;
import com.example.graphqlserver.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    
    public BookService(BookRepository b, AuthorRepository a) {
        this.bookRepo = b;
        this.authorRepo = a;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findByIsbn(String isbn) {
        return bookRepo.findById(isbn).orElse(null);
    }

    public Book save(String isbn, String title, Integer authorId) {
        Author author = authorRepo.findById(authorId).orElseThrow();
        Book book = new Book(isbn, title, author);
        return bookRepo.save(book);
    }

    public List<Book> findByAuthorId(Integer id) {
        return bookRepo.findByAuthorId(id);
    }

    public void deleteByIsbn(String isbn) {
        bookRepo.deleteById(isbn); 
    }
    public boolean existsByIsbn(String isbn) { 
        return bookRepo.existsById(isbn); 
    }
}
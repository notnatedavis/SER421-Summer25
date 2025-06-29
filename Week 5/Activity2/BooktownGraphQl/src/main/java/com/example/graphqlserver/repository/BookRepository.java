/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\repository\BookRepository.java
 * defines dummy repository for managing Book objs
 */
package com.example.graphqlserver.repository;

// imports
import com.example.graphqlserver.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class BookRepository {

    // dummy data initialization
    private static ArrayList<Book> dummyBooks = new ArrayList<>();
    static {
        dummyBooks.addAll(Arrays.asList(
                new Book("123456789", "The Road Not Taken", 0),
                new Book("987654321", "To Kill a Mockingbird", 1),
                new Book("456789123", "The Great Gatsby", 2)
        ));
    }

    // get all book(s)
    public List<Book> getBooks() {
        return dummyBooks;
    }

    // get all book(s) w/ isbn
    public Book getBookByISBN(String isbn) {
        for (Book book : dummyBooks) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // save new book
    public Book save(String isbn, String title, int authorId) {
        Book newBook = new Book(isbn, title, authorId);
        dummyBooks.add(newBook);
        return newBook;
    }

    // find book(s) w/ author's id
    public static ArrayList<Book> getBooksByAuthorId(int id) {
        ArrayList<Book> bookList = new ArrayList<>();
        for (Book book : dummyBooks) {
            if (book.getAuthorId() == id) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    // find author(s) w/ id
    public List<Book> findByAuthorId(Integer authorId) {
        List<Book> result = new ArrayList<>();
        for (Book book : dummyBooks) {
            if (Integer.valueOf(book.getAuthorId()).equals(authorId)) {
                result.add(book);
            }
        }
        return result;
    }

    // check if book(s) exists w/ isbn
    public boolean existsById(String isbn) {
        for (Book book : dummyBooks) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    // delete book(s) w/ isbn
    public void deleteById(String isbn) {
        dummyBooks.removeIf(book -> book.getIsbn().equals(isbn));
    }
}

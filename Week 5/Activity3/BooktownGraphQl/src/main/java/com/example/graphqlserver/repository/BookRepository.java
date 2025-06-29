/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\repository\BookRepository.java
 * Spring Data JPA interface that gives access to database operations for Book entities
 */
package com.example.graphqlserver.repository;

// imports
import com.example.graphqlserver.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// declare BookRepository interface which extends JpaRepository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByAuthorId(Integer authorId);
}
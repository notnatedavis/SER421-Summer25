/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\repository\AuthorRepository.java
 * Spring Data JPA interface that gives access to database operations for Author entities
 */
package com.example.graphqlserver.repository;

// imports
import com.example.graphqlserver.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// declare AuthorRepository interface which extends JpaRepository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByLastName(String lastName);
    List<Author> findByFirstName(String firstName);
}
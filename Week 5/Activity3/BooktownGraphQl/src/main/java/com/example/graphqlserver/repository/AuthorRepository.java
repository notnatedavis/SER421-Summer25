/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\repository\AuthorRepository.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.repository;

// imports
import com.example.graphqlserver.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByLastName(String lastName);
    List<Author> findByFirstName(String firstName);
}
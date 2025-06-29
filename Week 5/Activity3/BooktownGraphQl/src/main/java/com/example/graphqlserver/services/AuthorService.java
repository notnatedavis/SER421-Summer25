/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\services\AuthorService.java
 * handles all logic related to Author (between AuthorController & AuthorRepository)
 */
package com.example.graphqlserver.services;

// imports
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    // declaration
    private final AuthorRepository repo;

    // constructor
    public AuthorService(AuthorRepository repo) { this.repo = repo; }

    // service methods for author

    public List<Author> findAll() {
        return repo.findAll(); 
    }

    public Author findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Author save(Author author) {
        return repo.save(author); 
    }

    public List<Author> findByLastName(String lastName) {
        return repo.findByLastName(lastName); 
    }

    public List<Author> findByFirstName(String firstName) {
        return repo.findByFirstName(firstName);
    }
}
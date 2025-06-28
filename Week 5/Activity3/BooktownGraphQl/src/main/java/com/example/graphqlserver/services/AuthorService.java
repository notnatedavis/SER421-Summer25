// src\main\java\com\example\graphqlserver\services\AuthorService.java

package com.example.graphqlserver.services;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repo;
    public AuthorService(AuthorRepository repo) { this.repo = repo; }

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
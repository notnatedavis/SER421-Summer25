package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByLastName(String lastName);
    List<Author> findByFirstName(String firstName);
}
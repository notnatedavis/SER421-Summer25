package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByAuthorId(Integer authorId);
}
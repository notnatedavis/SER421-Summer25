/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\repository\AuthorRepository.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.repository;

// imports
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class AuthorRepository {
    private static ArrayList<Author> dummyAuthors = new ArrayList<>();

    static {
        Author author1 = new Author(0, "Robert", "Frost", BookRepository.getBooksByAuthorId(0));
        Author author2 = new Author(1, "Martin", "Fowler", BookRepository.getBooksByAuthorId(1));
        Author author3 = new Author(2, "Kevin", "Gary", BookRepository.getBooksByAuthorId(2));

        dummyAuthors.addAll(Arrays.asList(author1, author2, author3));
    }

    public List<Author> getAuthors() {
        return dummyAuthors;
    }

    public Author getAuthorById(int id) {
        for (Author author : dummyAuthors) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    public Author save(String firstName, String lastName) {
        List<Book> book = new ArrayList<>();
        int nextId = dummyAuthors.isEmpty() ? 0 : dummyAuthors.get(dummyAuthors.size() - 1).getId() + 1;
        Author newAuthor = new Author(nextId, firstName, lastName, book);
        dummyAuthors.add(newAuthor);
        return newAuthor;
    }

    // new method
    public List<Author> findByLastName(String lastName) {
        List<Author> result = new ArrayList<>();
        for (Author author : dummyAuthors) {
            if (author.getLastName().equals(lastName)) {
                result.add(author);
            }
        }
        return result;
    }

    // new method
    public List<Author> findByFirstName(String firstName) {
        List<Author> result = new ArrayList<>();
        for (Author author : dummyAuthors) {
            if (author.getFirstName().equals(firstName)) {
                result.add(author);
            }
        }
        return result;
    }
}

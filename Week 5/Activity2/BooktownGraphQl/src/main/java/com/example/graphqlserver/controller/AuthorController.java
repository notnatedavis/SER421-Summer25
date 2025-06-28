/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 6/27/25
 * 
 * src\main\java\com\example\graphqlserver\controller\AuthorController.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.controller;

// imports
import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AddAuthorPayload;
import com.example.graphqlserver.dto.input.UpdateAuthorFirstNameInput; // new import
import com.example.graphqlserver.dto.output.UpdateAuthorFirstNamePayload; // new import
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorRepository.getAuthors();
    }

    @QueryMapping
    public Author authorById(@Argument("id") int id) {
        return authorRepository.getAuthorById(id);
    }

    @MutationMapping
    public AddAuthorPayload addAuthor(@Argument AddAuthorInput input) {
        var author = authorRepository.save(input.firstName(), input.lastName());
        var out = new AddAuthorPayload(author);
        return out;
    }

    // Task 2 , list of Author(s) based on lastName
    @QueryMapping
    public List<Author> authorsByLastName(@Argument String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    // Task 3 , update an Author's firstName based on Author's Id
    @MutationMapping
    public UpdateAuthorFirstNamePayload updateAuthorFirstName(@Argument UpdateAuthorFirstNameInput input) {
        
        Author a = authorRepository.getAuthorById(Integer.parseInt(input.authorId()));

        if (a == null) {
            return new UpdateAuthorFirstNamePayload(null); 
        }

        // store old firstName in a temp, set new firstName , save + return old temp
        String oldName = a.getFirstName();
        a.setFirstName(input.newFirstName());
        authorRepository.save(a.getFirstName(), a.getLastName());
        return new UpdateAuthorFirstNamePayload(oldName);
    }

    // Task 5 , list of all Book title(s) by Author(s) w/ given firstName
    @QueryMapping
    public List<String> bookTitlesByAuthorFirstName(@Argument String firstName) {
        // collect all book titles of author from firstName via .stream
        return authorRepository.findByFirstName(firstName).stream()
            .flatMap(author -> author.getBooks().stream())
            .map(book -> book.getTitle())
            .toList();
    }
}

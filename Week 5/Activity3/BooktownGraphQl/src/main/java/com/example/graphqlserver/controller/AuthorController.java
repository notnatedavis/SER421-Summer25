/*
 * SER421-Summer25
 * Lab 6 , Activity 3
 * ndavispe , 6/28/25
 * 
 * src\main\java\com\example\graphqlserver\controller\AuthorController.java
 * [PURPOSE_OF_FILE_IN_RELATION]
 */
package com.example.graphqlserver.controller;

// imports
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import java.util.List;
import com.example.graphqlserver.services.AuthorService;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AddAuthorPayload;
import com.example.graphqlserver.dto.input.UpdateAuthorFirstNameInput;
import com.example.graphqlserver.dto.output.UpdateAuthorFirstNamePayload;

@Controller
public class AuthorController {

    private final AuthorService authorSvc; // author logic , set as priv final

    // construct
    public AuthorController(AuthorService authorSvc) {
        this.authorSvc = authorSvc;
    }

    // query for author(s)
    @QueryMapping public List<Author> authors() {
        return authorSvc.findAll();
    }

    // query for author(s) by id
    @QueryMapping public Author authorById(@Argument Integer id) {
        return authorSvc.findById(id);
    }

    // mutation for adding a new author
    @MutationMapping
    public AddAuthorPayload addAuthor(@Argument AddAuthorInput in) {
        Author saved = authorSvc.save(new Author(in.firstName(), in.lastName()));
        return new AddAuthorPayload(saved);
    }

    // query for author(s) w/ lastName
    @QueryMapping
    public List<Author> authorsByLastName(@Argument String lastName) {
        return authorSvc.findByLastName(lastName);
    }

    // query for book(s) w/ author's firstName
    @QueryMapping
    public List<String> bookTitlesByAuthorFirstName(@Argument String firstName) {
        return authorSvc.findByFirstName(firstName).stream()
                        .flatMap(a -> a.getBooks().stream())
                        .map(Book::getTitle)
                        .toList();
    }

    // mutation for updating author's firstName
    @MutationMapping
    public UpdateAuthorFirstNamePayload updateAuthorFirstName(@Argument UpdateAuthorFirstNameInput in) {
        
        Author a = authorSvc.findById(in.authorId());

        if (a == null) return new UpdateAuthorFirstNamePayload(null);

        String old = a.getFirstName();
        a.setFirstName(in.newFirstName());
        authorSvc.save(a);

        return new UpdateAuthorFirstNamePayload(old);
    }
}

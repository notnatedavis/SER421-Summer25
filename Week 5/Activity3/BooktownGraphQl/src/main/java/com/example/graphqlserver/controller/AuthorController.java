// src\main\java\com\example\graphqlserver\controller\AuthorController.java
package com.example.graphqlserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import java.util.List;

// import com.example.graphqlserver.dto.input.AddBookInput;
// import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.services.AuthorService;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AddAuthorPayload;
import com.example.graphqlserver.dto.input.UpdateAuthorFirstNameInput;
import com.example.graphqlserver.dto.output.UpdateAuthorFirstNamePayload;

// import com.example.graphqlserver.dto.input.DeleteBookByISBNInput;
// import com.example.graphqlserver.dto.output.DeleteBookByISBNPayload;

@Controller
public class AuthorController {

    private final AuthorService authorSvc;
    public AuthorController(AuthorService authorSvc) {
        this.authorSvc = authorSvc;
    }

    @QueryMapping public List<Author> authors() {
        return authorSvc.findAll();
    }

    @QueryMapping public Author authorById(@Argument Integer id) {
        return authorSvc.findById(id);
    }

    @MutationMapping
    public AddAuthorPayload addAuthor(@Argument AddAuthorInput in) {
        Author saved = authorSvc.save(new Author(in.firstName(), in.lastName()));
        return new AddAuthorPayload(saved);
    }

    @QueryMapping
    public List<Author> authorsByLastName(@Argument String lastName) {
        return authorSvc.findByLastName(lastName);
    }

    @QueryMapping
    public List<String> bookTitlesByAuthorFirstName(@Argument String firstName) {
        return authorSvc.findByFirstName(firstName).stream()
                        .flatMap(a -> a.getBooks().stream())
                        .map(Book::getTitle)
                        .toList();
    }

    @MutationMapping
    public UpdateAuthorFirstNamePayload updateAuthorFirstName(
             @Argument UpdateAuthorFirstNameInput in) {
        Author a = authorSvc.findById(in.authorId());
        if (a == null) return new UpdateAuthorFirstNamePayload(null);
        String old = a.getFirstName();
        a.setFirstName(in.newFirstName());
        authorSvc.save(a);
        return new UpdateAuthorFirstNamePayload(old);
    }
}

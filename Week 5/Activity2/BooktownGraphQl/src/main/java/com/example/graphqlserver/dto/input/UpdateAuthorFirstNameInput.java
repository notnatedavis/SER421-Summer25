// src\main\java\com\example\graphqlserver\dto\input\UpdateAuthorFirstNameInput.java

package com.example.graphqlserver.dto.input;

public record UpdateAuthorFirstNameInput(
    String authorId,
    String newFirstName
) {}